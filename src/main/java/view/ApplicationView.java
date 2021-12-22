package view;

import controller.*;
import dag.Vertex;

import javax.swing.*;
import java.awt.*;

/**
 * The application view class holds all the elements that are
 * involved in the view of our application, and sets up all
 * these elements in order to be seen by the user. Predominantly
 * uses swing and the grid bag constraints layout.
 *
 * @author Adrian Kucharski, King's College London - K1631110
 */
public class ApplicationView extends JFrame {

    private JList vertexList, vertexChildrenList, vertexParentList;
    private JTextArea graphInfoTA, superBubbleReportTA;
    private JPanel centerPanel, eastPanel, westPanel, southPanel;
    private JScrollPane vertexListScroll, vertexChildrenListScroll, vertexParentListScroll;
    private JButton addVertexButton, addVertexesButton, addChildButton, addParentButton,
            generateSuperBubbleReportButton, deleteChildButton, deleteParentButton, resetButton;
    private JTextField vertexTF, childTF, parentTF;
    private JLabel nameVertexChildren, nameVertexParents;

    private DefaultListModel<Vertex> vertexListModel, childrenListModel, parentsListModel;

    /**
     * Constructor which initialises all the list models which
     * store the information, and sets up the view of the
     * application.
     */
    public ApplicationView() {
        super("Super Bubble Identification App");
        vertexListModel = new DefaultListModel<>();
        childrenListModel = new DefaultListModel<>();
        parentsListModel = new DefaultListModel<>();
        setUp();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Setup method which runs all the set up methods, and
     * adds all the mouse and action listeners necessary to
     * all buttons and lists.
     */
    public void setUp() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        setUpListsAndTextAreas();
        vertexList.addMouseListener(new VertexListMouseListener(vertexList, nameVertexChildren, nameVertexParents,
                childrenListModel, parentsListModel));
        addVertexButton.addActionListener(new AddVertexAction(vertexListModel, graphInfoTA));
        addVertexesButton.addActionListener(new AddVertexesAction(vertexListModel, vertexTF, graphInfoTA));
        addChildButton.addActionListener(new AddChildAction(childTF, childrenListModel,
                vertexListModel, vertexList, graphInfoTA));
        addParentButton.addActionListener(new AddParentAction(parentTF, parentsListModel,
                vertexListModel, vertexList, graphInfoTA));
        generateSuperBubbleReportButton.addActionListener(new GenerateReportAction(vertexListModel,
                superBubbleReportTA));
        deleteChildButton.addActionListener(new DeleteChildActionListener(vertexChildrenList,
                vertexList, childrenListModel, graphInfoTA, vertexListModel));
        deleteParentButton.addActionListener(new DeleteParentActionListener(vertexParentList,
                vertexList, parentsListModel, graphInfoTA, vertexListModel));
        resetButton.addActionListener(new ResetButton(this));
    }

    /**
     * The start method makes the application visible, and
     * is necessary to be called in order to see the
     * view of the application.
     */
    public void start() {
        this.setVisible(true);
    }

    /**
     * The set up lists and text areas method calls sub-routines
     * which are used to set up various parts of the application
     * made using the grid bag layout.
     */
    public void setUpListsAndTextAreas() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 0, 0);
        GridBagLayout gbl = new GridBagLayout();
        setUpWestPanel(gbl, gbc);
        setUpCenterPanel(gbl, gbc);
        setUpEastPanel(gbl, gbc);
        setUpSouthPanel(gbl, gbc);
    }

    /**
     * The set up south panel method sets up the south panel of
     * the application. This refers to the reset and generate
     * super bubble buttons at the bottom of the application.
     *
     * @param gbl   GridBagLayout manager used for the whole
     *              application
     * @param gbc   GridBagConstraints used for the whole
     *              application
     */
    public void setUpSouthPanel(GridBagLayout gbl, GridBagConstraints gbc) {
        southPanel = new JPanel();
        generateSuperBubbleReportButton = new JButton("Generate Super Bubble Report");
        resetButton = new JButton("Reset");
        southPanel.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        southPanel.add(generateSuperBubbleReportButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        southPanel.add(resetButton, gbc);
        //southPanel.setBorder(new LineBorder(Color.black, 5));
        this.add(BorderLayout.SOUTH, southPanel);
    }

    /**
     * The set up west panel method sets up the west panel
     * of the whole application. This includes the vertexes
     * list with the add vertex button, add vertexes button
     * and label for add vertexes button.
     *
     * @param gbl   GridBagLayout manager used for the whole
     *              application
     * @param gbc   GridBagConstraints used for the whole
     *              application
     */
    public void setUpWestPanel(GridBagLayout gbl, GridBagConstraints gbc) {
        westPanel = new JPanel();
        westPanel.setLayout(gbl);
        JLabel name = new JLabel("Vertexes");
        vertexList = new JList(vertexListModel);
        vertexListScroll = new JScrollPane(vertexList);
        vertexListScroll.setHorizontalScrollBar(new JScrollBar());
        vertexListScroll.setPreferredSize(new Dimension(250, 400));
        gbc.gridx = 1;
        gbc.gridy = 0;
        westPanel.add(name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        westPanel.add(vertexListScroll, gbc);
        //westPanel.setBorder(new LineBorder(Color.black, 5));
        this.add(BorderLayout.WEST, westPanel);
    }

    /**
     * The set up center panel button sets up the middle
     * area of the application. This includes the children
     * and parents list, with all the button for adding
     * and deleting parents/children.
     *
     * @param gbl   GridBagLayout manager used for the whole
     *              application
     * @param gbc   GridBagConstraints used for the whole
     *              application
     */
    public void setUpCenterPanel(GridBagLayout gbl, GridBagConstraints gbc) {
        centerPanel = new JPanel();
        //do west panel
        JPanel west = new JPanel();
        west.setLayout(gbl);
        addVertexButton = new JButton("Add Vertex");
        vertexTF = new JTextField(10);
        addVertexesButton = new JButton("Add Vertexes");

        gbc.gridx = 1;
        gbc.gridy = 0;
        west.add(addVertexButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        west.add(vertexTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        west.add(addVertexesButton, gbc);
        west.setSize(150, 500);
        //west.setBorder(new LineBorder(Color.black, 5));

        //do center panel
        JPanel center = new JPanel();
        vertexChildrenList = new JList(childrenListModel);
        vertexParentList = new JList(parentsListModel);
        vertexChildrenListScroll = new JScrollPane(vertexChildrenList);
        vertexParentListScroll = new JScrollPane(vertexParentList);
        vertexParentListScroll.setHorizontalScrollBar(new JScrollBar());
        vertexChildrenListScroll.setHorizontalScrollBar(new JScrollBar());
        vertexChildrenListScroll.setPreferredSize(new Dimension(250, 200));
        vertexParentListScroll.setPreferredSize(new Dimension(250, 200));
        JPanel centerSouthPanel = new JPanel();
        JPanel centerNorthPanel = new JPanel();
        centerSouthPanel.setLayout(gbl);
        centerNorthPanel.setLayout(gbl);

        nameVertexChildren = new JLabel("Children of Vertex ");
        nameVertexParents = new JLabel("Parents of Vertex ");
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerNorthPanel.add(nameVertexChildren, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerNorthPanel.add(vertexChildrenListScroll, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerSouthPanel.add(nameVertexParents, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerSouthPanel.add(vertexParentListScroll, gbc);

        center.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        center.add(centerNorthPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        center.add(centerSouthPanel, gbc);
        //center.setSize(250,500);
        //center.setBorder(new LineBorder(Color.black, 5));

        //do east panel
        addChildButton = new JButton("Add Child");
        addParentButton = new JButton("Add Parent");
        deleteChildButton = new JButton("Delete Child");
        deleteParentButton = new JButton("Delete Parent");
        childTF = new JTextField(10);
        parentTF = new JTextField(10);

        JPanel east = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel northPanel = new JPanel();

        northPanel.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        northPanel.add(childTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        northPanel.add(addChildButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        northPanel.add(deleteChildButton, gbc);
        southPanel.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        southPanel.add(parentTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        southPanel.add(addParentButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        southPanel.add(deleteParentButton, gbc);

        east.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        east.add(northPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        east.add(southPanel, gbc);
        east.setSize(125, 500);
        //east.setBorder(new LineBorder(Color.black, 5));

        //finish set up
        centerPanel.add(BorderLayout.WEST, west);
        centerPanel.add(BorderLayout.CENTER, center);
        centerPanel.add(BorderLayout.EAST, east);
        centerPanel.setSize(500, 500);
        //centerPanel.setBorder(new LineBorder(Color.black, 5));
        this.add(BorderLayout.CENTER, centerPanel);
    }

    /**
     * The set up east panel method sets up the right
     * side of the application. This includes the graph
     * information section and the superbubble report info
     * section.
     *
     * @param gbl   GridBagLayout manager used for the whole
     *              application
     * @param gbc   GridBagConstraints used for the whole
     *              application
     */
    public void setUpEastPanel(GridBagLayout gbl, GridBagConstraints gbc) {
        eastPanel = new JPanel();
        graphInfoTA = new JTextArea(5, 20);
        graphInfoTA.setText("Vertexes in Graph: 0\nEdges in Graph: 0");
        superBubbleReportTA = new JTextArea(20, 20);
        graphInfoTA.setEditable(false);
        superBubbleReportTA.setEditable(false);
        JScrollPane graphPane = new JScrollPane(graphInfoTA);
        JScrollPane reportPane = new JScrollPane(superBubbleReportTA);
        graphPane.setHorizontalScrollBar(new JScrollBar());
        reportPane.setHorizontalScrollBar(new JScrollBar());
        graphPane.setPreferredSize(new Dimension(250, 200));
        reportPane.setPreferredSize(new Dimension(250, 200));
        JPanel north = new JPanel();
        north.setLayout(gbl);
        JPanel south = new JPanel();
        south.setLayout(gbl);
        JLabel graphInfoLabel = new JLabel("Graph Information:");
        JLabel superBubbleReportLabel = new JLabel("Super Bubble Report: ");
        gbc.gridx = 1;
        gbc.gridy = 0;
        north.add(graphInfoLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        north.add(graphPane, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        south.add(superBubbleReportLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        south.add(reportPane, gbc);
        eastPanel.setLayout(gbl);
        gbc.gridx = 1;
        gbc.gridy = 0;
        eastPanel.add(north, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        eastPanel.add(south, gbc);
        //eastPanel.setBorder(new LineBorder(Color.black, 5));
        this.add(BorderLayout.EAST, eastPanel);
    }
}
