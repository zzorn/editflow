package org.editflow.utils

import java.awt.{Toolkit, Component, Dimension}
import javax.swing.{JMenuBar, JComponent, JFrame}

/**
 * A Swing JFrame with sensible default settings.
 * Makes UI prototyping easier.
 */
class SimpleFrame(title: String = null,
                  content: JComponent = null,
                  menuBar: JMenuBar = null,
                  relativeSize: Double = 0.75,
                  minWidth: Int = 320,
                  minHeight: Int = 200) extends JFrame {

  buildUi()

  // Show
  setVisible(true)

  private def buildUi() {
    // Title
    if (title != null) setTitle(title)

    // Content & menu
    if (content != null) setContentPane(content)
    if (menuBar != null) setJMenuBar(menuBar)

    // Determine size
    val screenSize = Toolkit.getDefaultToolkit.getScreenSize
    val height = math.max(minHeight, (relativeSize * screenSize.height).toInt)
    val width = math.max(minWidth, (relativeSize * screenSize.width).toInt)
    setPreferredSize(new Dimension(width, height))
    setMinimumSize(new Dimension(minWidth, minHeight))

    // Close handling
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    // Layout
    pack()

    // Center on main display
    setLocationRelativeTo(null)
  }

}


