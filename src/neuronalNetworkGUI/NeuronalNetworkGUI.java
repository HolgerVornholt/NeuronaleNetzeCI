/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuronalNetworkGUI;
import neuronalNetwork.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.*;

/**
 *
 * @author HolgerVornholt
 */
public class NeuronalNetworkGUI extends javax.swing.JFrame {

    private DrawPanel drawPanel;
    private JPanel gridPanel;
    public boolean selectOrigin = true;
    public NeuronButton fromButton;
    public NeuronButton toButton;
    public boolean done = false;
    
    private Network myNetwork;
    private String[] possiblePropFunc;
    GridLayout gridPanelLayout;
    public int currentZoom = 1;
    private String[] zoomOptions = {"0","1","2","3","4"};
    private int[] neuronHeight = {33,69,128,186,246};
    private int[] neuronWidth = {34,68,127,186,245};
    private int gridHspace = 20;
    private int gridVspace = 50;
    
    String path = "Images/Neuron" + this.currentZoom +".png";
    java.net.URL imgURL = getClass().getResource(path);
    
    /**
     * Creates new form NeuronalNetworkGUI
     */
    public NeuronalNetworkGUI() {
        initComponents();
        this.possiblePropFunc = new Neuron().getPossiblePropFunc();
        propagationComboBox.setModel(new javax.swing.DefaultComboBoxModel(possiblePropFunc)); 
        zoomComboBox.setModel(new javax.swing.DefaultComboBoxModel(zoomOptions));
        
        gridPanel = new JPanel();
        drawPanel = new DrawPanel(this);
        drawPanel.setPreferredSize(new Dimension(500,500));
        drawPanel.setBackground(Color.white);
        gridPanel.setBackground(Color.red);
        drawPanel.add(gridPanel);
        gridPanelLayout = new GridLayout(1,0,gridHspace,gridVspace);
        gridPanel.setLayout(gridPanelLayout);
        gridPanel.setOpaque(true);
        
        treeScrollPane.setViewportView(drawPanel);
    }
    
    public Point getGridPanelLocation(){
        return gridPanel.getLocation();
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        leftPanel = new javax.swing.JPanel();
        leftPanelTop = new javax.swing.JPanel();
        networkLabel = new javax.swing.JLabel();
        layersTextField = new javax.swing.JTextField();
        layersLabel = new javax.swing.JLabel();
        applyNetworkButton = new javax.swing.JButton();
        leftPanelBot = new javax.swing.JPanel();
        learningLabel = new javax.swing.JLabel();
        ruleComboBox = new javax.swing.JComboBox();
        ruleLabel = new javax.swing.JLabel();
        trainingButton = new javax.swing.JButton();
        ruleLabel1 = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        paraLabel = new javax.swing.JLabel();
        learnRateTextField = new javax.swing.JTextField();
        maxItTextField = new javax.swing.JTextField();
        learnRateLabel = new javax.swing.JLabel();
        maxItLabel = new javax.swing.JLabel();
        stepButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        treeScrollPane = new javax.swing.JScrollPane();
        rightPanel = new javax.swing.JPanel();
        rightPanelTop = new javax.swing.JPanel();
        neuronsLabel = new javax.swing.JLabel();
        addRemoveLabel = new javax.swing.JLabel();
        whichLayerTextField = new javax.swing.JTextField();
        whichLayerLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        descLabel1 = new javax.swing.JLabel();
        descLabel2 = new javax.swing.JLabel();
        descLabel3 = new javax.swing.JLabel();
        addEditEdgeButton = new javax.swing.JButton();
        propagationComboBox = new javax.swing.JComboBox();
        propagationLabel = new javax.swing.JLabel();
        rightBottomPanel = new javax.swing.JPanel();
        viewLabel = new javax.swing.JLabel();
        zoomLabel = new javax.swing.JLabel();
        applyViewButton = new javax.swing.JButton();
        zoomComboBox = new javax.swing.JComboBox();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        leftPanelTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        networkLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        networkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        networkLabel.setText("Network");

        layersTextField.setText("0");

        layersLabel.setText("layers");

        applyNetworkButton.setText("Apply");
        applyNetworkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyNetworkButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelTopLayout = new javax.swing.GroupLayout(leftPanelTop);
        leftPanelTop.setLayout(leftPanelTopLayout);
        leftPanelTopLayout.setHorizontalGroup(
            leftPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(networkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(leftPanelTopLayout.createSequentialGroup()
                        .addComponent(layersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(layersLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(applyNetworkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        leftPanelTopLayout.setVerticalGroup(
            leftPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(networkLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(layersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(layersLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applyNetworkButton)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        leftPanelBot.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        learningLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        learningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        learningLabel.setText("Learning");

        ruleComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ruleLabel.setText("Select a rule:");

        trainingButton.setText("Select training file");
        trainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainingButtonActionPerformed(evt);
            }
        });

        ruleLabel1.setText("Path for training file:");

        paraLabel.setText("Parameters:");

        learnRateLabel.setText("learning rate");

        maxItLabel.setText("max iterations");

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelBotLayout = new javax.swing.GroupLayout(leftPanelBot);
        leftPanelBot.setLayout(leftPanelBotLayout);
        leftPanelBotLayout.setHorizontalGroup(
            leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelBotLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelBotLayout.createSequentialGroup()
                        .addComponent(paraLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(leftPanelBotLayout.createSequentialGroup()
                        .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(learningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(leftPanelBotLayout.createSequentialGroup()
                                .addComponent(stepButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(runButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ruleLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ruleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(leftPanelBotLayout.createSequentialGroup()
                        .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pathTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ruleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(leftPanelBotLayout.createSequentialGroup()
                        .addComponent(trainingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(leftPanelBotLayout.createSequentialGroup()
                        .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maxItTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(learnRateTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(learnRateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maxItLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addGap(12, 12, 12))))
        );
        leftPanelBotLayout.setVerticalGroup(
            leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelBotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(learningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ruleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ruleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ruleLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(trainingButton)
                .addGap(18, 18, 18)
                .addComponent(paraLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(learnRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(learnRateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxItTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxItLabel))
                .addGap(18, 18, 18)
                .addGroup(leftPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepButton)
                    .addComponent(runButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(leftPanelBot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(leftPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftPanelBot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        treeScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        treeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        rightPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rightPanelTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        neuronsLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        neuronsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        neuronsLabel.setText("Neurons");

        addRemoveLabel.setText("Add/Remove Neurons:");

        whichLayerLabel.setText("layer");

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        descLabel1.setText("To add/edit an edge,");

        descLabel2.setText("press the button and");

        descLabel3.setText("click on the two Neurons.");

        addEditEdgeButton.setText("Add/Edit edge");
        addEditEdgeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEditEdgeButtonActionPerformed(evt);
            }
        });

        propagationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        propagationLabel.setText("propagation");

        javax.swing.GroupLayout rightPanelTopLayout = new javax.swing.GroupLayout(rightPanelTop);
        rightPanelTop.setLayout(rightPanelTopLayout);
        rightPanelTopLayout.setHorizontalGroup(
            rightPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(neuronsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addEditEdgeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rightPanelTopLayout.createSequentialGroup()
                        .addGroup(rightPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addRemoveLabel)
                            .addGroup(rightPanelTopLayout.createSequentialGroup()
                                .addComponent(whichLayerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(whichLayerLabel))
                            .addComponent(descLabel1)
                            .addComponent(descLabel2)
                            .addGroup(rightPanelTopLayout.createSequentialGroup()
                                .addComponent(propagationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(propagationLabel))
                            .addGroup(rightPanelTopLayout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rightPanelTopLayout.setVerticalGroup(
            rightPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(neuronsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addRemoveLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(whichLayerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(whichLayerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(propagationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(propagationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(removeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(descLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addEditEdgeButton)
                .addContainerGap())
        );

        rightBottomPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        viewLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        viewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewLabel.setText("View");

        zoomLabel.setText("zoomoption");

        applyViewButton.setText("Apply");
        applyViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyViewButtonActionPerformed(evt);
            }
        });

        zoomComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout rightBottomPanelLayout = new javax.swing.GroupLayout(rightBottomPanel);
        rightBottomPanel.setLayout(rightBottomPanelLayout);
        rightBottomPanelLayout.setHorizontalGroup(
            rightBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightBottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(applyViewButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rightBottomPanelLayout.createSequentialGroup()
                        .addComponent(zoomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rightBottomPanelLayout.setVerticalGroup(
            rightBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightBottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zoomLabel)
                    .addComponent(zoomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applyViewButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rightPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightBottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addComponent(rightPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(treeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(treeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>   

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    	int targetLayer;
    	String propFunc;
    	try{
        	//throws NumberFormatException if not a Number.
    		targetLayer = Integer.parseInt(whichLayerTextField.getText())-1;
    		//throws NullPointerException if Network has not been initialized.
    		if (targetLayer < myNetwork.getLayerCount() && targetLayer >= 0){
    			
    			//adding a new Neuron might change the maxLayerSize to maxLayerSize + 1
    			propFunc = possiblePropFunc[propagationComboBox.getSelectedIndex()];
                myNetwork.addNeuron(targetLayer, new Neuron(propFunc));
                
                //Check if the maxLayerSize has changed and if so add a column to the gridlayout.
                int whereTo;
                if (myNetwork.maxLayerSize()> gridPanelLayout.getColumns()){
                	int newColumns = gridPanelLayout.getColumns()+1;
                	gridPanelLayout.setColumns(newColumns);            
                	//fill up the gridLayout with the new Neuron and empty labels where there are no new Neurons to be added.
                	for (int row = 0; row < myNetwork.getLayerCount();row++){
                		if (row == targetLayer){
                			//we want to put the Neuron behind the last existing one in a row.
                			whereTo = newColumns*row-1 + myNetwork.howManyInLayer(row);
                			gridPanel.add(new NeuronButton("" + myNetwork.getAddedNeurons(),targetLayer,myNetwork.howManyInLayer(targetLayer),this),whereTo);
                		} else {
                			// we need to add a label to the end of each row. if we have a gridlayout with 3 rows and 5 columns
                			// this means: add a label to index 4,9 and 14. this can be calculated by rowNumber*Columns-1 i.e for column 2: 3*2-1 = 9.
                			whereTo = newColumns*row-1 + newColumns;
                			gridPanel.add(new JLabel("neuerButton"),whereTo);
                		}                		
                	}
                //If the maxLayerSize has not been changed, a Label has to be replaced by the added Neuron.
                } else {
                	whereTo = gridPanelLayout.getColumns()*targetLayer-1 + myNetwork.howManyInLayer(targetLayer);
                	gridPanel.remove(whereTo);
                	gridPanel.add(new NeuronButton("" + myNetwork.getAddedNeurons(),targetLayer,myNetwork.howManyInLayer(targetLayer),this),whereTo);
                }
                
              //Calculate new Height and Width of gridPanel
                int width = (neuronWidth[currentZoom]+gridHspace)*gridPanelLayout.getColumns()-gridHspace;
                int height = (neuronHeight[currentZoom]+gridVspace)*gridPanelLayout.getRows()-gridVspace;
                gridPanel.setPreferredSize(new Dimension(width,height));
                drawPanel.setPreferredSize(new Dimension(width,height));
                gridPanel.setVisible(false);
                gridPanel.setVisible(true);
    		} else {
    			JOptionPane.showMessageDialog(this, "The layer can only be between 1 and " + (myNetwork.getLayerCount()));
    		}
            //NeuronButton geladen = (NeuronButton) gridPanel.getComponent(4);
            //geladen.setEnabled(false);
    		
        }  catch (NumberFormatException e){
        	JOptionPane.showMessageDialog(this, whichLayerTextField.getText() + " is not a valid layer");
        }  catch(NullPointerException e){
        	JOptionPane.showMessageDialog(this, "Please setup a Network first.");
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeButtonActionPerformed

    private void addEditEdgeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEditEdgeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addEditEdgeButtonActionPerformed

    private void applyNetworkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyNetworkButtonActionPerformed
        try{
        	myNetwork = new Network(Integer.parseInt(layersTextField.getText()));
        	gridPanel.removeAll();
        	gridPanelLayout.setColumns(0);
        	//needs check for range! (negative and 0 are not allowed)
        	gridPanelLayout.setRows(myNetwork.getLayerCount());
            gridPanel.setVisible(false);
            gridPanel.setVisible(true);
        } catch (NumberFormatException e){
        	JOptionPane.showMessageDialog(this, layersTextField.getText() + " is not a valid layer count.");
        }
   
    }//GEN-LAST:event_applyNetworkButtonActionPerformed

    private void applyViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyViewButtonActionPerformed
        currentZoom = Integer.parseInt(zoomOptions[zoomComboBox.getSelectedIndex()]);
        int width = (neuronWidth[currentZoom]+gridHspace)*gridPanelLayout.getColumns()-gridHspace;
        int height = (neuronHeight[currentZoom]+gridVspace)*gridPanelLayout.getRows()-gridVspace;
        gridPanel.setPreferredSize(new Dimension(width,height));
        drawPanel.setPreferredSize(new Dimension(width,height));
        
        
        path = "Images/Neuron" + this.currentZoom +".png";
        imgURL = getClass().getResource(path);
        Component[] gridComponents = gridPanel.getComponents();
        
        NeuronButton button;
        for (int i = 0; i < gridComponents.length; i++){
        	if(gridComponents[i].getClass() == new NeuronButton("",1,1,this).getClass()){
        		button = (NeuronButton) gridComponents[i];
        		button.setIcon(new ImageIcon(imgURL, ""));
        	}
        }
        
        gridPanel.setVisible(false);
        gridPanel.setVisible(true);
        
    }//GEN-LAST:event_applyViewButtonActionPerformed

    private void trainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trainingButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stepButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_runButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NeuronalNetworkGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NeuronalNetworkGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NeuronalNetworkGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NeuronalNetworkGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NeuronalNetworkGUI().setVisible(true);
            }
        });      
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton addEditEdgeButton;
    private javax.swing.JLabel addRemoveLabel;
    private javax.swing.JButton applyNetworkButton;
    private javax.swing.JButton applyViewButton;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel descLabel1;
    private javax.swing.JLabel descLabel2;
    private javax.swing.JLabel descLabel3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel layersLabel;
    private javax.swing.JTextField layersTextField;
    private javax.swing.JLabel learnRateLabel;
    private javax.swing.JTextField learnRateTextField;
    private javax.swing.JLabel learningLabel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel leftPanelBot;
    private javax.swing.JPanel leftPanelTop;
    private javax.swing.JLabel maxItLabel;
    private javax.swing.JTextField maxItTextField;
    private javax.swing.JLabel networkLabel;
    private javax.swing.JLabel neuronsLabel;
    private javax.swing.JLabel paraLabel;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JButton removeButton;
    private javax.swing.JPanel rightBottomPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel rightPanelTop;
    private javax.swing.JComboBox ruleComboBox;
    private javax.swing.JLabel ruleLabel;
    private javax.swing.JLabel ruleLabel1;
    private javax.swing.JButton runButton;
    private javax.swing.JButton stepButton;
    private javax.swing.JButton trainingButton;
    private javax.swing.JScrollPane treeScrollPane;
    private javax.swing.JLabel viewLabel;
    private javax.swing.JLabel whichLayerLabel;
    private javax.swing.JTextField whichLayerTextField;
    private javax.swing.JComboBox zoomComboBox;
    private javax.swing.JLabel zoomLabel;
    private javax.swing.JComboBox propagationComboBox;
    private javax.swing.JLabel propagationLabel;
    // End of variables declaration//GEN-END:variables
}
