package hw5;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class SamplePaint extends JFrame {

	private float zoom = 1;

	private JButton buttonLine, buttonCyrcle, buttonRectangle, buttonExit;

	private JRadioButton rdbtnGreen, rdbtnBlue, rdbtnRed, rdbtnBlack;

	private Graphics2D g2d, g2d1;
	private ButtonGroup buttonGroup, radioBttpnGroup;

	private Map<Point[], Color> lineMap, rectangleMap, cyrcleMap;

	private Point p1, p2;

	private User user;
	private ShapeManager shapeManager;
	private Shape shape;
	private Color color;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					User user = new User();
					user.setId(0);
					user.setPass("123");
					user.setUserName("guest");
					SamplePaint samplePaint = new SamplePaint(user);
					samplePaint.setVisible(true);
					// samplePaint.setResizable(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public SamplePaint(User user) {
		super("sample paint" + " : " + user.getUserName());

		this.user = new User();
		this.user.setId(user.getId());
		this.user.setUserName(user.getUserName());
		this.user.setPass(user.getPass());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initUI();

		rdbtnBlack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.BLACK;
			}
		});

		rdbtnRed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.RED;
			}
		});

		rdbtnGreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.GREEN;
			}
		});

		rdbtnBlue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.BLUE;
			}
		});

		buttonLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shape.setType("line");
			}
		});

		buttonCyrcle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shape.setType("cyrcle");
			}
		});

		buttonRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shape.setType("rectangle");
			}
		});

		buttonExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginOrSignUp().setVisible(true);

				setVisible(false);
			}
		});

		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				if (e.getPreciseWheelRotation() < 0) {
					zoom -= 0.1;
				} else {
					zoom += 0.1;
				}

//				if (zoom < 0.01) {
//					zoom = 0.01f;
//				}

				DecimalFormat df = new DecimalFormat("0.#");

				zoom = Float.parseFloat(df.format(zoom));

				repaint();

			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = new Point(e.getPoint());

				Point[] points = { p1, p2 };

				shape.setColor(color);
				shape.setX1((int) points[0].getX());
				shape.setY1((int) points[0].getY());
				shape.setX2((int) points[1].getX());
				shape.setY2((int) points[1].getY());

				shapeManager.addShape(shape);

				switch (shape.getType()) {

				case "cyrcle":
					cyrcleMap.put(points, color);

					break;
				case "rectangle":
					rectangleMap.put(points, color);

					break;
				default:
					lineMap.put(points, color);

					break;
				}

				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {

				p1 = new Point(e.getPoint());

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

	private void initUI() {

		shapeManager = new ShapeManager(user);

		shape = new Shape();
		shape.setColor(Color.BLACK);
		shape.setType("line");

		color = Color.BLACK;

		lineMap = new HashMap<Point[], Color>();

		cyrcleMap = new HashMap<Point[], Color>();

		rectangleMap = new HashMap<Point[], Color>();

		lineMap = shapeManager.getShape("line");
		cyrcleMap = shapeManager.getShape("cyrcle");
		rectangleMap = shapeManager.getShape("rectangle");

		setBounds(100, 100, 630, 550);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(100, 100, 500, 500);
		contentPane.setLayout(null);

		buttonLine = new JButton("خط");
		buttonLine.setBounds(520, 10, 89, 23);
		contentPane.add(buttonLine);

		buttonCyrcle = new JButton("دایره");
		buttonCyrcle.setBounds(520, 40, 89, 23);
		contentPane.add(buttonCyrcle);

		buttonRectangle = new JButton("مستطیل");
		buttonRectangle.setBounds(520, 70, 89, 23);
		contentPane.add(buttonRectangle);

		JLabel label = new JLabel("انتخاب رنگ");
		label.setBounds(540, 110, 79, 23);
		contentPane.add(label);

		rdbtnBlack = new JRadioButton("مشکی");
		rdbtnBlack.setBounds(540, 140, 70, 23);
		contentPane.add(rdbtnBlack);

		rdbtnRed = new JRadioButton("قرمز");
		rdbtnRed.setBounds(540, 170, 70, 23);
		contentPane.add(rdbtnRed);

		rdbtnGreen = new JRadioButton("سبز");
		rdbtnGreen.setBounds(540, 200, 70, 23);
		contentPane.add(rdbtnGreen);

		rdbtnBlue = new JRadioButton("آبی");
		rdbtnBlue.setBounds(540, 230, 70, 23);
		contentPane.add(rdbtnBlue);

		radioBttpnGroup = new ButtonGroup();
		radioBttpnGroup.add(rdbtnBlack);
		radioBttpnGroup.add(rdbtnBlue);
		radioBttpnGroup.add(rdbtnGreen);
		radioBttpnGroup.add(rdbtnRed);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(buttonRectangle);
		buttonGroup.add(buttonCyrcle);
		buttonGroup.add(buttonLine);

		buttonExit = new JButton("خروج");
		buttonExit.setBounds(520, 270, 89, 23);
		contentPane.add(buttonExit);

		// contentPane.add(new OptionsPanel(),BorderLayout.EAST);;
		setContentPane(contentPane);
	}

	public void paint(Graphics g) {
		super.paint(g);

		g2d = (Graphics2D) g;
		g2d1 = (Graphics2D) contentPane.getGraphics();
		double width = getWidth();
		double height = getHeight();

		double zoomWidth = width * zoom;
		double zoomHeight = height * zoom;

		double anchorx = (width - zoomWidth) / 2;
		double anchory = (height - zoomHeight) / 2;

		AffineTransform at = new AffineTransform();
		at.translate(anchorx, anchory);
		at.scale(zoom, zoom);

//		 at.translate(-100, -100);

		g2d.setTransform(at);

//		if (zoom < 1) {
			// g2d1 = (Graphics2D) g.create();

//			g2d.rotate(360);

			drawShape(g2d);

//		} else if (zoom > 1) {
//			drawShape(g2d);
//
//		} else {
//			drawShape(g2d);
//
//		}

	}

	private void drawShape(Graphics2D graphicss2d) {

		// cyrcle
		drawAllCyrcles(graphicss2d);

		// Rectangle ;
		drewAllRectangles(graphicss2d);

		// Linee
		drawAllLines(graphicss2d);

	}

	private void drawAllCyrcles(Graphics2D graphicss2d) {

		for (Point[] points : cyrcleMap.keySet()) {
			graphicss2d.setColor(cyrcleMap.get(points));
			if (points[1].getX() > points[0].getX()) {
				if (points[1].getY() > points[0].getY()) {

					int width = (int) (points[1].getX() - points[0].getX()) * 2;
					int height = (int) (points[1].getY() - points[0].getY()) * 2;

					graphicss2d.drawOval((int) (points[0].getX()),
							(int) (points[0].getY()), width, height);
				} else if (points[1].getY() < points[0].getY()) {
					int width = (int) (points[1].getX() - points[0].getX());
					int height = (int) (points[0].getY() - points[1].getY());

					graphicss2d.drawOval((int) (points[0].getX()),
							(int) (points[1].getY() - height), width * 2,
							height * 2);

				}

			} else if (points[1].getX() < points[0].getX()) {
				if (points[1].getY() > points[0].getY()) {
					int width = (int) (points[0].getX() - points[1].getX());
					int height = (int) (points[1].getY() - points[0].getY());

					graphicss2d.drawOval((int) (points[1].getX() - width),
							(int) (points[0].getY()), width * 2, height * 2);

				}
				if (points[1].getY() < points[0].getY()) {
					int width = (int) (points[0].getX() - points[1].getX());
					int height = (int) (points[0].getY() - points[1].getY());

					graphicss2d.drawOval((int) (points[1].getX() - (width)),
							(int) (points[1].getY() - (height)), width * 2,
							height * 2);

				}
			} else if (points[1].getX() == points[0].getX()) {
				if (points[1].getY() > points[0].getY()) {
					int width = (int) (points[1].getY() - points[0].getY());
					int height = width;

					graphicss2d.drawOval(
							(int) (points[0].getX() - (width) / 2),
							(int) (points[0].getY()), width, height);
				} else if (points[1].getY() < points[0].getY()) {
					int width = (int) (points[1].getY() - points[0].getY());
					int height = width;

					graphicss2d.drawOval(
							(int) (points[0].getX() - (width) / 2),
							(int) (points[1].getY() - (height) / 2), width,
							height);
				}
			} else if (points[1].getY() == points[0].getY()) {
				if (points[1].getX() > points[0].getX()) {
					int width = (int) (points[1].getX() - points[0].getX());
					int height = width;

					graphicss2d.drawOval((int) (points[0].getX()),
							(int) (points[0].getY() - (height) / 2), width,
							height);

				} else if (points[1].getX() < points[0].getX()) {
					int width = (int) (points[0].getX() - points[0].getX());
					int height = width;

					graphicss2d.drawOval(
							(int) (points[1].getX() - (width) / 2),
							(int) (points[1].getY() - (height) / 2), width,
							height);

				}
			}

		}

	}

	private void drawAllLines(Graphics2D graphicss2d) {

		for (Point[] points : lineMap.keySet()) {

			graphicss2d.setColor(lineMap.get(points));
			// graphicss2d.setColor(Color.red);
			graphicss2d.drawLine((int) points[0].getX(),
					(int) points[0].getY(), (int) points[1].getX(),
					(int) points[1].getY());
		}
	}

	private void drewAllRectangles(Graphics2D graphicss2d) {
		for (Point[] points : rectangleMap.keySet()) {
			graphicss2d.setColor(rectangleMap.get(points));
			if (points[1].getX() > points[0].getX()) {
				if (points[1].getY() > points[0].getY()) {
					graphicss2d.drawRect((int) points[0].getX(),
							(int) points[0].getY(),
							(int) (points[1].getX() - points[0].getX()),
							(int) (+points[1].getY() - points[0].getY()));
				} else if (points[1].getY() < points[0].getY()) {
					graphicss2d.drawRect((int) points[0].getX(),
							(int) points[1].getY(),
							(int) (points[1].getX() - points[0].getX()),
							(int) (-points[1].getY() + points[0].getY()));
				}
			} else if (points[1].getX() < points[0].getX()) {
				if (points[1].getY() > points[0].getY()) {
					graphicss2d.drawRect((int) points[1].getX(),
							(int) points[0].getY(),
							(int) (points[0].getX() - points[1].getX()),
							(int) (points[1].getY() - points[0].getY()));

				} else if (points[1].getY() < points[0].getY()) {
					graphicss2d.drawRect((int) points[1].getX(),
							(int) points[1].getY(),
							(int) (points[0].getX() - points[1].getX()),
							(int) (points[0].getY() - points[1].getY()));

				}
			}
		}
	}

}