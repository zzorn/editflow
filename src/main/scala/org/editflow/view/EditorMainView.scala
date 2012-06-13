package org.editflow.view

import javax.swing._
import java.awt._
import org.editflow.EditorContext
import org.editflow.utils._
import java.awt.event.ActionEvent

/**
 *
 */
class EditorMainView(context: EditorContext) extends MainView {

  private var frame: JFrame = null


  def start() {
    buildUi()
  }


  protected def buildUi() {

    val mainPanel: JComponent = new JPanel(new BorderLayout())

    val dockPanel = new DockPanel()

    val menuBar = createMenu()
    val toolBar = createToolbar()

    val library = context.libraryView.component

    mainPanel.add(toolBar, BorderLayout.NORTH)
    mainPanel.add(dockPanel.component, BorderLayout.CENTER)

    frame = new SimpleFrame(context.applicationName, mainPanel, menuBar, 0.8)

    dockPanel.addPanel(new JPanel())
    dockPanel.addPanel(library, LeftSide, 200, 0.2)
    dockPanel.addPanel(new JLabel("foo"), TopSide, 100, 0)
    dockPanel.addPanel(new JLabel("bar"), RightSide, 500, 0.5, true)
    frame.pack()
  }


  protected def createMenu(): JMenuBar = {

    val menu1: JMenu = new JMenu("Test")

    menu1.getPopupMenu.setLightWeightPopupEnabled(context.useLightweightComponents)

    menu1.add(new JMenuItem("Test Item"))
    menu1.add(new JMenuItem("Test Item2"))

    val menuBar = new JMenuBar()
    menuBar.add(menu1)
    menuBar
  }

  protected def createToolbar(): JComponent = {
    val toolbar: JToolBar = new JToolBar()
    toolbar.setFloatable(false)
    toolbar.add(new AbstractAction("Exit") {
      def actionPerformed(e: ActionEvent) {
        System.exit(1)
      }
    })

    toolbar
  }



}