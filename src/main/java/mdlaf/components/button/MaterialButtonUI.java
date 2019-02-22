package mdlaf.components.button;

import javafx.scene.paint.Material;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialDrawingUtils;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MaterialButtonUI extends BasicButtonUI {

	public static ComponentUI createUI (final JComponent c) {
		return new MaterialButtonUI ();
	}

	@Override
	public void installUI (JComponent c) {
		super.installUI (c);

		AbstractButton button = (AbstractButton) c;
		button.setOpaque (UIManager.getBoolean ("Button.opaque"));
		button.setBorder (UIManager.getBorder ("Button.border"));
		button.setBackground (UIManager.getColor ("Button.background"));
		button.setForeground (UIManager.getColor ("Button.foreground"));
		button.setFont (UIManager.getFont ("Button.font"));
		button.addChangeListener(new EffectButton());
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void paint (Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		g = MaterialDrawingUtils.getAliasedGraphics (g);

		if (b.isContentAreaFilled ()) {
			paintBackground (g, b);
		}

		super.paint (g, c);
	}

	private void paintBackground (Graphics g, JComponent c) {
		g.setColor (c.getBackground ());
		g.fillRoundRect (0, 0, c.getWidth (), c.getHeight (), 7, 7);
	}

	private class EffectButton implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JComponent component = (JComponent) e.getSource();
			if(UIManager.getBoolean("Button.mouseHoverEnable")){
				MaterialUIMovement.add(component, MaterialColors.bleach(UIManager.getColor("Button.mouseHoverColor"), (float) 0.1), 5, 1000 / 30);
			}
		}


	}
}