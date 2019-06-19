import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
 
public class GameFrame extends JFrame implements ActionListener {
    private static GameFrame frame;
    private JPanel backPanel, centerPanel, bottomPanel;
    private JButton btnOK, btnNextGeneration, btnStart, btnStop, btnExit;
    private JButton[][] btnBlock;
    private JLabel lblRow, lblCol;
    private JComboBox rowList, colList;
    private boolean[][] isSelected;
    private int maxRow, maxCol;
    private Life life;
    private boolean isRunning;
    private Thread thread;
    private boolean isDead;
 
    public static void main(String arg[]) {
        frame = new GameFrame("������Ϸ");
    }
 
    public int getMaxRow() {
        return maxRow;
    }
 
    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }
 
    public int getMaxCol() {
        return maxCol;
    }
 
    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }
 
    public void initGUI() {
        /**
         * ��Ƶ�ͼ���������� *
         */
        if (maxRow == 0) {
            maxRow = 20;
        }
 
        if (maxCol == 0) {
            maxCol = 60;
        }
 
        life = new Life(maxRow, maxCol);
 
        backPanel = new JPanel(new BorderLayout());//��������
        centerPanel = new JPanel(new GridLayout(maxRow, maxCol));
        bottomPanel = new JPanel();
        rowList = new JComboBox();//�˵���
        for (int i = 3; i <= 20; i++) {
            rowList.addItem(String.valueOf(i));
        }
        colList = new JComboBox();
        for (int i = 3; i <= 60; i++) {
            colList.addItem(String.valueOf(i));
        }
        rowList.setSelectedIndex(maxRow - 3);//ѡ�񵥸���Ԫ
        colList.setSelectedIndex(maxCol - 3);
        btnOK = new JButton("ȷ��");
        btnNextGeneration = new JButton("��һ��");
        btnBlock = new JButton[maxRow][maxCol];
        btnStart = new JButton("��ʼ");
        btnStop = new JButton("ֹͣ");
        btnExit = new JButton("�˳�");
        isSelected = new boolean[maxRow][maxCol];
        lblRow = new JLabel("����������");
        lblCol = new JLabel("����������");
        this.setContentPane(backPanel);
 
        backPanel.add(centerPanel, "Center");
        backPanel.add(bottomPanel, "South");
 
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                btnBlock[i][j] = new JButton("");
                btnBlock[i][j].setBackground(Color.WHITE);
                centerPanel.add(btnBlock[i][j]);
            }
        }
 
        bottomPanel.add(lblRow);
        bottomPanel.add(rowList);
        bottomPanel.add(lblCol);
        bottomPanel.add(colList);
        bottomPanel.add(btnOK);
        bottomPanel.add(btnNextGeneration);
        bottomPanel.add(btnStart);
        bottomPanel.add(btnStop);
        bottomPanel.add(btnExit);
 
        // ���ô���
        this.setSize(900, 620);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // �ô�������Ļ����
 
        // ����������Ϊ�ɼ���
        this.setVisible(true);
 
        // ע�������
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        btnOK.addActionListener(this);
        btnNextGeneration.addActionListener(this);
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
        btnExit.addActionListener(this);
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                btnBlock[i][j].addActionListener(this);
            }
        }
    }
 
    public GameFrame(String name) {
        super(name);
        initGUI();
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOK) {
            frame.setMaxRow(rowList.getSelectedIndex() + 3);
            frame.setMaxCol(colList.getSelectedIndex() + 3);
            initGUI();
            life = new Life(getMaxRow(), getMaxCol());
        } else if (e.getSource() == btnNextGeneration) {
            makeNextGeneration();
        } else if (e.getSource() == btnStart) {
            isRunning = true;
            thread = new Thread(new Runnable() {//�����߳�
                @Override
                public void run() {
                    while (isRunning) {
                        makeNextGeneration();
                        boolean isSame = true;
                        try {
                            Thread.sleep(500);//�����ݱ�ʱ����0.5��
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        isDead = true;
                        for(int row = 1; row <= maxRow; row++) {
                            for (int col = 1; col <= maxCol; col++) {
                                if (life.getGrid()[row][col] != 0) {
                                    isDead = false;
                                    break;
                                }
                            }
                            if (!isDead) {
                                break;
                            }
                        }
                        if (isDead) {
                            JOptionPane.showMessageDialog(null, "������ʧ��~");
                            isRunning = false;
                            thread = null;
                        }
                    }
                }
            });
            thread.start();
        } else if (e.getSource() == btnStop) {
            isRunning = false;
            thread = null;
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        } else {
            int[][] grid = life.getGrid();
            for (int i = 0; i < maxRow; i++) {
                for (int j = 0; j < maxCol; j++) {
                    if (e.getSource() == btnBlock[i][j]) {
                        isSelected[i][j] = !isSelected[i][j];
                        if (isSelected[i][j]) {
                            btnBlock[i][j].setBackground(Color.BLACK);
                            grid[i + 1][j + 1] = 1;
                        } else {
                            btnBlock[i][j].setBackground(Color.WHITE);
                            grid[i + 1][j + 1] = 0;
                        }
                        break;
                    }
                }
            }
            life.setGrid(grid);
        }
    }
 
    private void makeNextGeneration() {//�����ݱ���һ��
        life.update();
        int[][] grid = life.getGrid();
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (grid[i + 1][j + 1] == 1) {
                    btnBlock[i][j].setBackground(Color.BLACK);
                } else {
                    btnBlock[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}