package org.editflow.utils

import java.awt.BorderLayout
import javax.swing.{JSplitPane, JPanel, JComponent}


/**
 *
 */
class DockPanel {

  private val hostPanel: JComponent = new JPanel(new BorderLayout())
  private var latestContent: JComponent = null

  def component: JComponent = hostPanel

  def addPanel[T <: JComponent](component: T,
                                side: Side = RightSide,
                                panelSize: Int = 200,
                                resizeWeight: Double = 0.5,
                                continuousResize: Boolean = false): T = {
    if (latestContent == null) {
      hostPanel.add(component)
      latestContent = component
      component
    }
    else {
      val split = if (side.first) new JSplitPane(side.axis, component, latestContent)
      else                        new JSplitPane(side.axis, latestContent, component)

      hostPanel.remove(latestContent)

      latestContent = split

      hostPanel.add(latestContent)

      if (side.first) {
        split.setDividerLocation(panelSize)
        split.setResizeWeight(resizeWeight)
      }
      else {
        split.setDividerLocation(hostPanel.getWidth - panelSize)
        split.setResizeWeight(1.0 - resizeWeight)
      }

      //split.setOneTouchExpandable(true)
      split.setContinuousLayout(continuousResize)

      component
    }
  }
}

sealed case class Side(axis: Int, first: Boolean)

case object LeftSide extends Side(JSplitPane.HORIZONTAL_SPLIT, true)
case object RightSide extends Side(JSplitPane.HORIZONTAL_SPLIT, false)
case object TopSide extends Side(JSplitPane.VERTICAL_SPLIT, true)
case object BottomSide extends Side(JSplitPane.VERTICAL_SPLIT, false)
