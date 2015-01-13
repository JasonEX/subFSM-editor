package fr.lusis.tools.subFSM.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mxgraph.util.mxResources;

public class EditorAboutFrame extends com.mxgraph.examples.swing.editor.EditorAboutFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8133561285545010458L;

	public EditorAboutFrame(Frame owner)
	{
		super(owner);
		setTitle(mxResources.get("aboutGraphEditor"));
		setLayout(new BorderLayout());

		// Creates the gradient panel
		JPanel panel = new JPanel(new BorderLayout())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -5062895855016210947L;

			/**
			 * 
			 */
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);

				// Paint gradient background
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(new GradientPaint(0, 0, Color.WHITE, getWidth(),
						0, getBackground()));
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}

		};

		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createMatteBorder(0, 0, 1, 0, Color.GRAY), BorderFactory
				.createEmptyBorder(8, 8, 12, 8)));

		// Adds title
		JLabel titleLabel = new JLabel(mxResources.get("aboutGraphEditor"));
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
		titleLabel.setOpaque(false);
		panel.add(titleLabel, BorderLayout.NORTH);

		// Adds optional subtitle
		JLabel subtitleLabel = new JLabel(
				"Sub-FSM graph editor for LUSIS");
		subtitleLabel.setBorder(BorderFactory.createEmptyBorder(4, 18, 0, 0));
		subtitleLabel.setOpaque(false);
		panel.add(subtitleLabel, BorderLayout.CENTER);

		getContentPane().add(panel, BorderLayout.NORTH);

		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		content.add(new JLabel("A sub-FSM graph editor for internal usage"));
//		content.add(new JLabel(" "));

		content.add(new JLabel("based on JGraphX library"));
		content.add(new JLabel(" "));

		content.add(new JLabel("Yuqian YANG - LUSIS"));
		content.add(new JLabel(" "));
		
		JButton link = new JButton();
		link.setText("https://github.com/JasonEX/subFSM-editor");
		link.setHorizontalAlignment(SwingConstants.LEFT);
		link.setBorderPainted(false);
		link.setOpaque(false);
		link.setBackground(Color.WHITE);
		link.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
				      try {
				        Desktop.getDesktop().browse(new URI("https://github.com/JasonEX/subFSM-editor"));
				      } catch (Exception ex) {
				    	  ex.printStackTrace();
				      }
				    } else { 
				}
				
			}
		});
		content.add(link);
		content.add(new JLabel(" "));

		try
		{
			content.add(new JLabel("Operating System Name: "
					+ System.getProperty("os.name")));
			content.add(new JLabel("Operating System Version: "
					+ System.getProperty("os.version")));
			content.add(new JLabel(" "));

			content.add(new JLabel("Java Vendor: "
					+ System.getProperty("java.vendor", "undefined")));
			content.add(new JLabel("Java Version: "
					+ System.getProperty("java.version", "undefined")));
			content.add(new JLabel(" "));

			content.add(new JLabel("Total Memory: "
					+ Runtime.getRuntime().totalMemory()));
			content.add(new JLabel("Free Memory: "
					+ Runtime.getRuntime().freeMemory()));
		}
		catch (Exception e)
		{
			// ignore
		}

		getContentPane().add(content, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createMatteBorder(1, 0, 0, 0, Color.GRAY), BorderFactory
				.createEmptyBorder(16, 8, 8, 8)));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		// Adds OK button to close window
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});

		buttonPanel.add(closeButton);

		// Sets default button for enter key
		getRootPane().setDefaultButton(closeButton);

		setResizable(false);
		setSize(400, 400);
	}
	

}
