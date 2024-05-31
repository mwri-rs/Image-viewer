import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;
public class ImageViewerGUI extends JFrame implements ActionListener{
        JButton selectFileButton = new JButton("Choose Image");
        JButton showImageButton = new JButton("Show Image");
        JButton resizeButton = new JButton("Resize");
        JButton grayscaleButton = new JButton("Gray Scale");
        JButton brightnessButton = new JButton("Brightness");
        JButton closeButton = new JButton("Exit");
        JButton showResizeButton = new JButton("Result");
        JButton showBrightnessButton = new JButton("Result");
        JButton backButton = new JButton("Back");
        JTextField widthTextField = new JTextField();
        JTextField heightTextField = new JTextField();
        JTextField brightnessTextField = new JTextField();

        String filePath = "C:\\Users\\asus\\Desktop\\java\\AP\\projects\\ImageViewer\\photos";
        File file;
        JFileChooser fileChooser = new JFileChooser(filePath);
        int h = 900;
        int w = 1200;
        float brightenFactor = 1;

        ImageViewerGUI(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Image Viewer");
            this.setSize(700, 300);
            this.setVisible(true);
            this.setResizable(true);

            mainPanel();
        }

        public void mainPanel(){
            // Create main panel for adding to Frame
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null);

            // Create Grid panel for adding buttons to it, then add it all to main panel
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(3, 2));

            buttonsPanel.setBounds(100,100,500,125);
            JLabel imageViewer = new JLabel("Image Viewer");
            imageViewer.setBounds(310,60,100,30);
            mainPanel.add(imageViewer);

            // Adding all buttons to Grid panel
            buttonsPanel.add(this.selectFileButton);
            selectFileButton.addActionListener(this);

            buttonsPanel.add(this.showImageButton);
            showImageButton.addActionListener(this);

            buttonsPanel.add(this.brightnessButton);
            this.brightnessButton.addActionListener(this);

            buttonsPanel.add(this.grayscaleButton);
            grayscaleButton.addActionListener(this);

            buttonsPanel.add(this.resizeButton);
            resizeButton.addActionListener(this);

            buttonsPanel.add(this.closeButton);
            closeButton.addActionListener(this);

            // add Grid panel that contains 6 buttons to main panel
            mainPanel.add(buttonsPanel);

            // add main panel to our frame
            this.add(mainPanel);
        }

        public void resizePanel(){
            JPanel resizePanel = new JPanel();
            resizePanel.setLayout(null);

            JLabel width = new JLabel("Width:");
            JLabel height = new JLabel("Height:");
            JLabel title = new JLabel("Resize Section");

            width.setBounds(100 , 80 , 100,30);
            height.setBounds(100 , 130 , 100 ,30 );
            title.setBounds(300 , 20 , 100 ,100);

            resizePanel.add(width);
            resizePanel.add(height);
            resizePanel.add(title);

            this.widthTextField.setBounds(250 , 80 , 200 , 30);
            this.heightTextField.setBounds(250 , 130 , 200 , 30);

            resizePanel.add(widthTextField);
            resizePanel.add(heightTextField);

            this.showResizeButton.setBounds(450 , 200 , 100 , 50);
            this.backButton.setBounds(100 , 200 , 100 , 50);

            showResizeButton.addActionListener(this);
            backButton.addActionListener(this);

            resizePanel.add(showResizeButton);
            this.add(backButton);

            this.add(resizePanel);
        }

        public void brightnessPanel(){
            JPanel brightnessPanel = new JPanel();
            brightnessPanel.setLayout(null);

            JLabel enterBox = new JLabel("Enter f");
            JLabel range = new JLabel("(must be between 0 and 1)");
            enterBox.setBounds(100 , 80 , 100 , 30);
            range.setBounds(80 , 90 , 200 , 50);

            brightnessPanel.add(enterBox);
            brightnessPanel.add(range);


            this.brightnessTextField.setBounds(250,80,200,50);

            brightnessPanel.add(brightnessTextField);

            this.backButton.setBounds(90,200,100,50);
            this.showBrightnessButton.setBounds(450,200,100,50);

            showBrightnessButton.addActionListener(this);
            backButton.addActionListener(this);

            brightnessPanel.add(showBrightnessButton);
            brightnessPanel.add(backButton);


            this.add(brightnessPanel);
        }

        public void chooseFileImage(){
            fileChooser.setAcceptAllFileFilterUsed(false);

            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(800 , 600 , Image.SCALE_DEFAULT));
            }

        }
        public void showOriginalImage(){
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JLabel label = new JLabel();

            try {

                BufferedImage bufferedimage = ImageIO.read(this.file);
                ImageIcon imageIcon = new ImageIcon(bufferedimage);

                label.setIcon(imageIcon);
                tempPanel.add(label);
            }

            catch (Exception ex){
                System.out.println("something went wrong");
            }

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        }

        public void grayScaleImage(){
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JLabel label = new JLabel();

            try {

                ImageFilter filter = new GrayFilter(false, 50);
                BufferedImage bufferedimage = ImageIO.read(this.file);
                ImageProducer producer = new FilteredImageSource(bufferedimage.getSource(), filter);
                Image mage = Toolkit.getDefaultToolkit().createImage(producer);

                ImageIcon imageIcon = new ImageIcon(mage);
                label.setIcon(imageIcon);
                tempPanel.add(label);

            }

            catch (Exception e){
                System.out.println("something went wrong");
            }

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        }
        public void showResizeImage(int w, int h){
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JLabel label = new JLabel();

            try {

                BufferedImage bufferedimage = ImageIO.read(this.file);
                Image newImage = bufferedimage.getScaledInstance(w, h, Image.SCALE_DEFAULT);
                ImageIcon imageIcon = new ImageIcon(newImage);
                label.setIcon(imageIcon);
                tempPanel.add(label);

            }

            catch (Exception e){
                System.out.println("something went wrong");
            }

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        }
        public void showBrightnessImage(float f){
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JLabel label = new JLabel();
            try {

                BufferedImage bufferedimage = ImageIO.read(this.file);
                RescaleOp op = new RescaleOp(f, 0, null);
                bufferedimage = op.filter(bufferedimage, bufferedimage);
                ImageIcon imageIcon = new ImageIcon(bufferedimage);

                label.setIcon(imageIcon);
                tempPanel.add(label);
            }

            catch (Exception ex){
                System.out.println("something went wrong");
            }

            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==resizeButton){
                this.getContentPane().removeAll();
                resizePanel();
                this.revalidate();
                this.repaint();

            }else if(e.getSource()== showImageButton){
                this.showOriginalImage();

            }else if(e.getSource()==grayscaleButton){
                this.grayScaleImage();

            }else if(e.getSource()== showResizeButton){
                showResizeImage(Integer.parseInt(widthTextField.getText()) , Integer.parseInt(heightTextField.getText()));

            }else if(e.getSource()==brightnessButton){
                this.getContentPane().removeAll();
                brightnessPanel();
                this.revalidate();
                this.repaint();

            }else if(e.getSource()== showBrightnessButton){
                showBrightnessImage(Float.parseFloat(brightnessTextField.getText()));


            }else if(e.getSource()== selectFileButton){
                chooseFileImage();

            }else if(e.getSource()==closeButton){
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else if(e.getSource()==backButton){
                this.getContentPane().removeAll();
                this.mainPanel();
                this.revalidate();
                this.repaint();
            }
        }
    }

