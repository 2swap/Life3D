/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cube;

/**
 *
 * @author AMH177
 */
public class Cube {

	public static int size = 50;
	public static boolean[][][] cube = new boolean[size][size][size];

	public static void iterate() {
		boolean[][][] newCube = new boolean[size][size][size];
		if (Input.space)
			return;
		for (int z = 1; z < size - 1; z++)
			for (int y = 1; y < size - 1; y++)
				for (int x = 1; x < size - 1; x++) {
					int surr = 0;
					for (int xn = -1; xn < 2; xn++)
						for (int yn = -1; yn < 2; yn++)
							for (int zn = -1; zn < 2; zn++)
								if (xn != 0 || yn != 0 || zn != 0)
									if (cube[x + xn][y + yn][z + zn])
										surr++;
					boolean col = false;
					if (surr == 3 || surr == 4)
						col = cube[x][y][z];
					if (surr == 5)
						col = true;
					newCube[x][y][z] = col;
				}
		for (int z = 1; z < size - 1; z++)
			for (int y = 1; y < size - 1; y++)
				for (int x = 1; x < size - 1; x++)
					cube[x][y][z] = newCube[x][y][z];
	}

	public static void init() {
		for (int x = -3; x < 4; x++)
			for (int y = -3; y < 4; y++)
				for (int z = -3; z < 4; z++)
					if (Math.random() < .2)
						cube[25 + x][25 + y][25 + z] = true;
	}
}
