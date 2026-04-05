import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class StudentFormDialog extends JDialog {

    JTextField name=new JTextField(), roll=new JTextField(),
            grade=new JTextField(), email=new JTextField();
    Integer id;

    public StudentFormDialog(DashboardFrame parent, Integer id) {
        super(parent,true);
        this.id=id;

        setTitle(id==null?"Add Student":"Edit Student");
        setSize(300,250);
        setLayout(new GridLayout(5,2,10,10));
        setLocationRelativeTo(parent);

        JButton save=new JButton("Save");
        save.addActionListener(e -> save(parent));

        add(new JLabel("Name")); add(name);
        add(new JLabel("Roll")); add(roll);
        add(new JLabel("Grade")); add(grade);
        add(new JLabel("Email")); add(email);
        add(new JLabel()); add(save);

        if(id!=null) load();
    }

    void load() {
        try (Connection c = Database.connect()) {
            PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM students WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                name.setText(rs.getString("name"));
                roll.setText(rs.getString("roll"));
                grade.setText(rs.getString("grade"));
                email.setText(rs.getString("email"));
            }
        } catch(Exception e){e.printStackTrace();}
    }

    void save(DashboardFrame parent) {
        if(name.getText().isEmpty()||roll.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Required fields");
            return;
        }

        try (Connection c = Database.connect()) {
            PreparedStatement ps;

            if(id==null){
                ps=c.prepareStatement(
                        "INSERT INTO students(name,roll,grade,email) VALUES(?,?,?,?)");
            } else {
                ps=c.prepareStatement(
                        "UPDATE students SET name=?,roll=?,grade=?,email=? WHERE id=?");
            }

            ps.setString(1,name.getText());
            ps.setString(2,roll.getText());
            ps.setString(3,grade.getText());
            ps.setString(4,email.getText());
            if(id!=null) ps.setInt(5,id);

            ps.executeUpdate();
            parent.refresh();
            dispose();

        } catch(Exception e){
            JOptionPane.showMessageDialog(this,"Error saving");
        }
    }
}
