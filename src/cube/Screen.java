package cube;

public class Screen {

	public int width;
	public int height;
	public int[] pixels;
	public Game game;
	public static double cx = 0, cy = 0, angle = 0;
	public static int runs = 1;
	public boolean rainbow = true;

	public Screen(int width, int height, Game game) {
		pixels = new int[width * height];
		for (int i = 0; i < width * height; i++)
			pixels[i] = 0;
		this.game = game;
		this.width = width;
		this.height = height;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}

	public void render() {
		double cAng = Math.cos(angle), sAng = Math.sin(angle);
		for (int py = 0; py < height; py++)
			for (int px = 0; px < width; px++) {
				double x = 0 + sAng * 5 + cAng * (px - width / 2.) / 20.;
				double y = 1 + (py - height / 2.) / 20.;
				double z = 0 + cAng * 5 - sAng * (px - width / 2.) / 20.;
				for (int i = 0; i < 100; i++) {
					x -= .1 * sAng;
					z -= .1 * cAng;
					if (!Cube.cube[(int) (x + Cube.size / 2)][(int) (y + Cube.size / 2)][(int) (z + Cube.size / 2)])
						continue;
					int edger = 0;
					if ((x + 500) % 1 < .1)
						edger++;
					if ((y + 500) % 1 < .1)
						edger++;
					if ((z + 500) % 1 < .1)
						edger++;
					if ((x + 500) % 1 > .9)
						edger++;
					if ((y + 500) % 1 > .9)
						edger++;
					if ((z + 500) % 1 > .9)
						edger++;
					pixels[px + py * width] = edger >= 2 ? 128 : 255;
					break;
				}
			}
	}
}
