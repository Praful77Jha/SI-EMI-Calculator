import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// ─── Main Application Class ───────────────────────────────────────────────
public class CalculatorApp extends JFrame {
    public CalculatorApp() {
        setTitle("Simple Interest / EMI Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Simple Interest", new SIPanel());
        tabbedPane.addTab("EMI Calculator", new EMIPanel());
        add(tabbedPane);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp());
    }
}
// ─── Simple Interest Panel ────────────────────────────────────────────────
class SIPanel extends JPanel implements ActionListener {
    private JTextField tfPrincipal, tfRate, tfTime;
    private JLabel lblResult;
    public SIPanel() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(new JLabel("Principal (P):"));
        tfPrincipal = new JTextField(); add(tfPrincipal);
        add(new JLabel("Rate of Interest (R %):"));
        tfRate = new JTextField(); add(tfRate);
        add(new JLabel("Time (T in years):"));
        tfTime = new JTextField(); add(tfTime);
        JButton btnCalc = new JButton("Calculate");
        btnCalc.addActionListener(this);
        add(btnCalc);
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearFields());
        add(btnClear);
        lblResult = new JLabel("Result: ");
        lblResult.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblResult);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double P = Double.parseDouble(tfPrincipal.getText().trim());
            double R = Double.parseDouble(tfRate.getText().trim());
            double T = Double.parseDouble(tfTime.getText().trim());
            double SI = (P * R * T) / 100;
            double total = P + SI;
            lblResult.setText(String.format(
                    "SI = %.2f | Total = %.2f", SI, total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearFields() {
        tfPrincipal.setText("");
        tfRate.setText("");
        tfTime.setText("");
        lblResult.setText("Result: ");
    }
}
// ─── EMI Calculator Panel ────────────────────────────────────────────────
class EMIPanel extends JPanel implements ActionListener {
    private JTextField tfLoan, tfAnnualRate, tfTenure;
    private JLabel lblResult;
    public EMIPanel() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(new JLabel("Loan Amount (P):"));
        tfLoan = new JTextField(); add(tfLoan);
        add(new JLabel("Annual Interest Rate (%):"));
        tfAnnualRate = new JTextField(); add(tfAnnualRate);
        add(new JLabel("Tenure (months):"));
        tfTenure = new JTextField(); add(tfTenure);
        JButton btnCalc = new JButton("Calculate EMI");
        btnCalc.addActionListener(this);
        add(btnCalc);
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearFields());
        add(btnClear);
        lblResult = new JLabel("EMI: ");
        lblResult.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblResult);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double P = Double.parseDouble(tfLoan.getText().trim());
            double annualRate =
                    Double.parseDouble(tfAnnualRate.getText().trim());
            int N = Integer.parseInt(tfTenure.getText().trim());
            double R = annualRate / 12 / 100; // Monthly interest rate
            double emi = (P * R * Math.pow(1 + R, N))
                    / (Math.pow(1 + R, N) - 1);
            double totalPayment = emi * N;
            double totalInterest = totalPayment - P;
            lblResult.setText(String.format(
                    "EMI = %.2f | Total = %.2f | Interest = %.2f",
                    emi, totalPayment, totalInterest));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearFields() {
        tfLoan.setText("");
        tfAnnualRate.setText("");
        tfTenure.setText("");
        lblResult.setText("EMI: ");
    }
}
