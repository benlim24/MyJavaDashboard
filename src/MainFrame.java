import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.knowm.xchart.*;

public class MainFrame extends JFrame{
    final private Font headerFont = new Font("Roboto",Font.PLAIN,40);
    final private Font header2Font = new Font("Open Sans",Font.BOLD,30);
    final private Font buttonFont = new Font("Open Sans",Font.PLAIN,20);
    final private Font paragrphFont = new Font("Open Sans",Font.PLAIN,20);
    JLabel lblHeaderLabel, lblAboutLabel;
    
    public void initialise () {
        /***** About Section*****/
        lblAboutLabel = new JLabel("This is just a demo project. Author: Lim Zhan Yi (Ben)");
        lblAboutLabel.setFont(paragrphFont);
        JPanel AboutPanel = new JPanel();
        AboutPanel.setLayout(new BorderLayout());
        AboutPanel.add(lblAboutLabel, BorderLayout.NORTH);

        /***** Top Section *****/
        /*** Website Name and Banner ***/
        lblHeaderLabel = new JLabel(" My Dashboard");
        lblHeaderLabel.setFont(headerFont);
        lblHeaderLabel.setForeground(Color.WHITE);
        lblHeaderLabel.setPreferredSize(new Dimension(MAXIMIZED_HORIZ,70));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1,1,10,10));
        headerPanel.setBackground(new Color(1,1,1));
        headerPanel.add(lblHeaderLabel);

        /***** Middle Section *****/
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        /*** Left Menu ***/
        JButton sideBarButton1 = new JButton("My Dashboard");
        sideBarButton1.setFont(buttonFont);
        sideBarButton1.setFocusPainted(false);
        sideBarButton1.setBackground(Color.WHITE);
        
        JButton sideBarButton2 = new JButton("About");
        sideBarButton2.setFont(buttonFont);
        sideBarButton2.setFocusPainted(false);
        sideBarButton2.setBackground(Color.WHITE);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(10,1,0,0));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setSize(new Dimension(100,10));
        menuPanel.add(sideBarButton1);
        menuPanel.add(sideBarButton2);
        
        /*** Main Body Panel (Center)***/     
        /** Create a Pie Chart **/
        PieChart salesChart = new PieChartBuilder().width(900).height(600).title("This Month's Car Sales (Units Sold)").build();

        Color[] sliceColors = new Color[]{new Color(201,45,37), new Color(40,190,200), new Color(30,20,160)};
        salesChart.getStyler().setSeriesColors(sliceColors);
        
        salesChart.addSeries("Myvi",500);
        salesChart.addSeries("Axia",400);
        salesChart.addSeries("Bezza",200);
        
        JPanel salesChartPanel = new XChartPanel<PieChart>(salesChart);

        /** Create a Bar Chart **/
        CategoryChart barChart = new CategoryChartBuilder().width(900).height(600).title("Monthly Gross Profit (2024)").xAxisTitle("Month").yAxisTitle("Profit(RM)").build();
        
        barChart.addSeries("Profit (RM)", new double[]{1,2,3,4,5,6,7}, new double[]{1005100,2030400,1506705,1345646,2457345,2746475,2800000});
        
        JPanel barChartPanel = new XChartPanel<CategoryChart>(barChart);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new CardLayout());

        JPanel chartsPanel = new JPanel();
        chartsPanel.setLayout(new GridLayout(1,2,5,5));
        chartsPanel.setBackground(Color.WHITE);
        chartsPanel.add(barChartPanel);
        chartsPanel.add(salesChartPanel);

        /** Create an announcement panel**/
        JLabel announcementTitle = new JLabel("Announcement");
        announcementTitle.setFont(header2Font);

        JLabel announcement1 = new JLabel("NEW: Monthly Sales Report for July 2024");
        announcement1.setFont(paragrphFont);

        JLabel announcement2 = new JLabel("NEW: New company policy (effective 22nd July 2024)");
        announcement2.setFont(paragrphFont);

        JLabel announcement3 = new JLabel("Staff promotions (effective 1st July 2024)");
        announcement3.setFont(paragrphFont);

        JPanel announcementPanel = new JPanel();
        announcementPanel.setLayout(new GridLayout(10,1,0,0));
        announcementPanel.setBackground(Color.WHITE);
        announcementPanel.add(announcementTitle);
        announcementPanel.add(announcement1);
        announcementPanel.add(announcement2);
        announcementPanel.add(announcement3);

        JPanel dashboardBodyPanel = new JPanel();
        dashboardBodyPanel.setLayout(new GridLayout(2,1,5,5));
        dashboardBodyPanel.setBackground(Color.WHITE);
        dashboardBodyPanel.add(chartsPanel);
        dashboardBodyPanel.add(announcementPanel);
        
        GridBagConstraints menuPanelConstr = new GridBagConstraints();
        menuPanelConstr.gridx = 0;
        menuPanelConstr.gridy = 0;
        menuPanelConstr.fill = GridBagConstraints.BOTH;
        middlePanel.add(menuPanel, menuPanelConstr);
        
        GridBagConstraints dashboardBodyPanelConstr = new GridBagConstraints();
        dashboardBodyPanelConstr.gridx = 1;
        dashboardBodyPanelConstr.gridy = 0;
        dashboardBodyPanelConstr.weightx = MAXIMIZED_HORIZ;
        dashboardBodyPanelConstr.weighty = MAXIMIZED_VERT;
        dashboardBodyPanelConstr.gridwidth = GridBagConstraints.REMAINDER;
        dashboardBodyPanelConstr.fill = GridBagConstraints.BOTH;
        dashboardBodyPanelConstr.insets = new Insets(15,15,15,15);
        middlePanel.add(dashboardBodyPanel, dashboardBodyPanelConstr);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel,BorderLayout.CENTER);

        //Add action listeners for sidebar buttons
        sideBarButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent e){
                lblHeaderLabel.setText(" My Dashboard");
                middlePanel.remove(AboutPanel);
                middlePanel.add(dashboardBodyPanel, dashboardBodyPanelConstr);
                repaint();
            }

        });

        sideBarButton2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed (ActionEvent e){
                lblHeaderLabel.setText(" About");
                middlePanel.remove(dashboardBodyPanel);
                GridBagConstraints AboutLabelPanelConstr = new GridBagConstraints();
                AboutLabelPanelConstr.gridx = 1;
                AboutLabelPanelConstr.gridy = 0;
                AboutLabelPanelConstr.weightx = MAXIMIZED_HORIZ;
                AboutLabelPanelConstr.weighty = MAXIMIZED_VERT;
                AboutLabelPanelConstr.gridwidth = GridBagConstraints.REMAINDER;
                AboutLabelPanelConstr.fill = GridBagConstraints.BOTH;
                AboutLabelPanelConstr.insets = new Insets(15,15,15,15);
                middlePanel.add(AboutPanel, AboutLabelPanelConstr);
                repaint();
            }

        });

        add(mainPanel);

        setTitle("Dashboard");
        setSize(1100,800);
        setMinimumSize(new Dimension(700,400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        mainFrame.initialise();
    }
}




