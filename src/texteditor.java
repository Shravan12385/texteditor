import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class texteditor implements ActionListener {

    //creating frame;
    JFrame frame;

    //declaring menubar
    JMenuBar menubar;

    //declaring textArea;

    JTextArea textArea;

    //menus
    JMenu file, edit;
    //declaring menuitems

    JMenuItem newfile,savefile,newwindow;

    //edit menuitems
    JMenuItem save,selectAll,copy,paste,cut,close;
    //constructor
    texteditor()
    {
        //intializting frame
        frame = new JFrame();

        //intializing menu
        menubar = new JMenuBar();

        //intializing textArea

        textArea = new JTextArea();

        //intialinzing menubar
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //intializing file menuitems
        newfile = new JMenuItem("newfile");
        savefile = new JMenuItem("saveFile");
        newwindow = new JMenuItem("newwindow");

        //ActionPerformed before adding to file menu
        newfile.addActionListener(this);
        savefile.addActionListener(this);
        newwindow.addActionListener(this);

        //adding to file menu
        file.add(newfile);
        file.add(savefile);
        file.add(newwindow);

        //intializing edit menuitems
        save = new JMenuItem("save");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut = new JMenuItem("cut");
        close = new JMenuItem("close");

        //add action to edit menuitems

        save.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        cut.addActionListener(this);
        close.addActionListener(this);

        //adding to edit menu;

        edit.add(save);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(cut);
        edit.add(close);



        menubar.add(file);
        menubar.add(edit);


        //setting in frame;
        frame.setJMenuBar(menubar);

        //adding to frame
//        frame.add(textArea);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));


        //add text ot panel
        panel.add(textArea,BorderLayout.CENTER);

        //jscroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        frame.add(panel);
        frame.setTitle("text");


        frame.setBounds(40,40,400,400);
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource()==close)
        {
            //perform close operation
            System.exit(0);

        }
        if(actionEvent.getSource()==cut)
        {
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==paste)
        {
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll)
        {
            //perform selectAll
            textArea.selectAll();
        }
        if(actionEvent.getSource()==copy)
        {
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==save)
        {
            //perform save operation


        }
        if(actionEvent.getSource()==savefile)
        {
            //perform savefile
            JFileChooser fileChooser = new JFileChooser("c:");

            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try {
                    //filr writer
                    FileWriter fileWriter = new FileWriter(file);

                    //buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    //write contents of textArea to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(actionEvent.getSource()==newfile)
        {
            //perform newfile operation

            //picking file picker
            JFileChooser fileChooser = new JFileChooser("c:");

            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();

                String filepath = file.getPath();

                try {
                    //intitalize file reader
                    FileReader fileReader = new FileReader(file);

                    //intitalize buffer reader

                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    //line by line reading

                    String intermediate="" , output="";

                    while ((intermediate=bufferedReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";
                    }
                    textArea.setText(   output);


                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(actionEvent.getSource()==newwindow)
        {
            //perform newwindow
            texteditor newtexteditor = new texteditor();
        }
    }

    public static void main(String[] args) {
        texteditor txdt = new texteditor();
    }


}