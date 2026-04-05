import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DashboardFrame extends JFrame {

    JTable table = new JTable();
    JTextField searchField = new JTextField(15);

    public DashboardFrame() {
        setTitle("Student Management");
        setSize(850,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton del = new JButton("Delete");
        JButton searchBtn = new JButton("Search");

        add.addActionListener(e -> new StudentFormDialog(this,null).setVisible(true));
        edit.addActionListener(e -> editStudent());
        del.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudent());
        searchField.addActionListener(e -> searchStudent());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        top.add(new JLabel("Search:"));
        top.add(searchField);
        top.add(searchBtn);
        top.add(add);
        top.add(edit);
        top.add(del);

        add(top,BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.CENTER);

        // 🔥 WATERMARK
        JLabel watermark = new JLabel("© Student System by Krishna", SwingConstants.CENTER);
        watermark.setForeground(new Color(0,0,0,80));
        add(watermark, BorderLayout.SOUTH);

        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2) editStudent();
            }
        });

        refresh();
    }

    void refresh() {
        loadTable("SELECT * FROM students", null);
    }

    void searchStudent() {
        String q = "%" + searchField.getText().trim().toLowerCase() + "%";

        String sql = """
            SELECT * FROM students WHERE
            LOWER(name) LIKE ? OR LOWER(roll) LIKE ?
        """;

        loadTable(sql,q);
    }

    void loadTable(String sql, String pattern) {
        try (Connection c = Database.connect()) {
            PreparedStatement ps = c.prepareStatement(sql);

            if(pattern!=null){
                ps.setString(1,pattern);
                ps.setString(2,pattern);
            }

            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel(){
                public boolean isCellEditable(int r,int c){return false;}
            };

            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Roll");
            model.addColumn("Grade");
            model.addColumn("Email");

            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll"),
                        rs.getString("grade"),
                        rs.getString("email")
                });
            }

            table.setModel(model);

        } catch(Exception e){e.printStackTrace();}
    }

    void editStudent() {
        int row = table.getSelectedRow();
        if(row<0){JOptionPane.showMessageDialog(this,"Select row");return;}

        int id = (int)table.getValueAt(row,0);
        new StudentFormDialog(this,id).setVisible(true);
    }

    void deleteStudent() {
        int row = table.getSelectedRow();
        if(row<0){JOptionPane.showMessageDialog(this,"Select row");return;}

        int id = (int)table.getValueAt(row,0);

        int confirm = JOptionPane.showConfirmDialog(this,"Delete?");

        if(confirm!=0) return;

        try(Connection c = Database.connect()){
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM students WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            refresh();
        } catch(Exception e){e.printStackTrace();}
    }
}
