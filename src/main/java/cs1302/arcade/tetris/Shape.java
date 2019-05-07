package cs1302.arcade.tetris;

import cs1302.arcade.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public enum Shape {
	
	T {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TetrisTile[] newCoordinates(int x, int y, int rotation, TetrisBoard board) {
			Tile[] c = new TetrisTile[4];
			c[0] = board.getTile(y, x);
			switch (rotation) {
			case 0:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y - 1, x);
				break;
			case 1:
				c[1] = board.getTile(y, x + 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			case 2:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y + 1, x);
				break;
			case 3:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			}
			return (TetrisTile[]) c;
		}
	},
	
	J {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TetrisTile[] newCoordinates(int x, int y, int rotation, TetrisBoard board) {
			Tile[] c = new TetrisTile[4];
			c[0] = board.getTile(y, x);
			switch (rotation) {
			case 0:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y - 1, x - 1);
				break;
			case 1:
				c[1] = board.getTile(y - 1, x + 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			case 2:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y + 1, x + 1);
				break;
			case 3:
				c[1] = board.getTile(y + 1, x - 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			}
			return (TetrisTile[]) c;
		}
	},

	Z {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TetrisTile[] newCoordinates(int x, int y, int rotation, TetrisBoard board) {
			Tile[] c = new TetrisTile[4];
			c[0] = board.getTile(y, x);
			switch (rotation) {
			case 1:
				c[1] = board.getTile(y - 1, x + 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y + 1, x);
				break;
			case 2:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y + 1, x + 1);
				c[3] = board.getTile(y + 1, x);
				break;
			}
			return (TetrisTile[]) c;
		}
	},

	O {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TetrisTile[] newCoordinates(int x, int y, int rotation, TetrisBoard board) {
			Tile[] c = new TetrisTile[4];
			c[0] = board.getTile(y, x);
			c[1] = board.getTile(y, x - 1);
			c[2] = board.getTile(y + 1, x - 1);
			c[3] = board.getTile(y + 1, x);
			return (TetrisTile[]) c;
		}
	},

	S {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TetrisTile[] newCoordinates(int x, int y, int rotation, TetrisBoard board) {
			Tile[] c = new TetrisTile[4];
			c[0] = board.getTile(y, x);
			switch (rotation) {
			case 1:
				c[1] = board.getTile(y, x + 1);
				c[2] = board.getTile(y + 1, x + 1);
				c[3] = board.getTile(y - 1, x);
				break;
			case 2:
				c[1] = board.getTile(y + 1, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y + 1, x);
				break;
			}
			return (TetrisTile[]) c;
		}
	},

	L {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TetrisTile[] newCoordinates(int x, int y, int rotation, TetrisBoard board) {
			Tile[] c = new TetrisTile[4];
			c[0] = board.getTile(y, x);
			switch (rotation) {
			case 0:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y - 1, x + 1);
				break;
			case 1:
				c[1] = board.getTile(y + 1, x + 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			case 2:
				c[1] = board.getTile(y, x - 1);
				c[2] = board.getTile(y, x + 1);
				c[3] = board.getTile(y + 1, x - 1);
				break;
			case 3:
				c[1] = board.getTile(y - 1, x - 1);
				c[2] = board.getTile(y + 1, x);
				c[3] = board.getTile(y - 1, x);
				break;
			}
			return (TetrisTile[]) c;
		}
	},

	I {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public TetrisTile[] newCoordinates(int x, int y, int rotation, TetrisBoard board) {
			Tile[] c = new TetrisTile[4];
			c[0] = board.getTile(y, x);
			switch (rotation) {
			case 1:
				c[1] = board.getTile(y - 2, x);
				c[2] = board.getTile(y - 1, x);
				c[3] = board.getTile(y + 1, x);
				break;
			case 2:
				c[1] = board.getTile(y, x - 2);
				c[2] = board.getTile(y, x - 1);
				c[3] = board.getTile(y, x + 1);
				break;
			}
			return (TetrisTile[]) c;
		}
	};

	final static PixelReader tile1 = new Image("/tetris/tile1.png").getPixelReader();
	final static PixelReader tile2 = new Image("/tetris/tile2.png").getPixelReader();
	
	
	/**
	 * Returns the coordinates occupied by this shape at the given
	 * location and rotatino
	 * 
	 * @param x
	 * @param y
	 * @param rotation
	 * @param board the board to return Tiles from
	 * @return an array of Tiles occupied by this shape
	 */
	public abstract TetrisTile[] newCoordinates(int x, int y, int rotation,
			TetrisBoard board);
	
	/**
	 * Returns a certain tile image depending on shape
	 * 
	 * @return p
	 */
	public PixelReader image() {
		switch (this) {
		case T:
		case O:
		case I:
			return tile1;
		}
		return tile2;
	}

	/**
	 * Returns the color used during a given level
	 * 
	 * @param level
	 * @return the color at this level
	 */
	public int getColor(int level) {
		if (this == Z || this == L) {
			return getColor2(level);
		}
		return getColor1(level);
	}
	
	/**
	 * Returns the first color used for a level
	 * 
	 * @param level
	 * @return
	 */
	public static int getColor1(int level) {
		switch (level % 10) {
		case 0:
			return 0xFF0959E0;
		case 1:
			return 0xFF209511;
		case 2:
			return 0xFFCA06BE;
		case 3:
			return 0xFF1C4AD3;
		case 4:
			return 0xFFD5095D;
		case 5:
			return 0xFF60F2A1;
		case 6:
			return 0xFFE23817;
		case 7:
			return 0xFF4423AE;
		case 8:
			return 0xFF1157E0;
		case 9:
			return 0xFFE5411A;
		}
		return 0;
	}
	
	/**
	 * Returns the second color used for a level
	 * 
	 * @param level
	 * @return
	 */
	public static int getColor2(int level) {
		switch (level % 10) {
		case 0:
			return 0xFF59B6FC;
		case 1:
			return 0xFFBEFD44;
		case 2:
			return 0xFFEE81E7;
		case 3:
			return 0xFF6DDA5D;
		case 4:
			return 0xFF66FBAA;
		case 5:
			return 0xFF758FE8;
		case 6:
			return 0xFF6F8581;
		case 7:
			return 0xFF980423;
		case 8:
			return 0xFFE94518;
		case 9:
			return 0xFFFBAB65;
		}
		return 0;
	}
	
	/**
	 * Returns a next board with layout dependent on Shape
	 * 
	 * @param t
	 * @return
	 */
	public TetrisBoard nextBoard(Tetris t) {
		switch (this) {
		case I:
			return new TetrisBoard(1, 4, t, 392, 220);
		case O:
			return new TetrisBoard(2, 2, t, 392, 220);
		case L:
		case Z:
		case S:
		case J:
		case T:
			return new TetrisBoard(2, 3, t, 392, 220);
		}
		return null;
	}
}
