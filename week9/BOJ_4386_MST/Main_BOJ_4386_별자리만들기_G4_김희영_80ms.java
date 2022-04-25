package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_4386_별자리만들기_G4_김희영_80ms {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[][] star = new double[N][2];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			star[i][0] = Double.parseDouble(st.nextToken()); // x
			star[i][1] = Double.parseDouble(st.nextToken()); // y
		}

		double[][] distance = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double d = Math.sqrt(Math.pow(star[i][0] - star[j][0], 2) + Math.pow(star[i][1] - star[j][1], 2));
				distance[i][j] = distance[j][i] = d;
			}
		}

		double sum = 0;
		boolean[] v = new boolean[N];
		double[] minDist = new double[N];
		Arrays.fill(minDist, Double.MAX_VALUE);
		minDist[0] = 0;

		for (int n = 0; n < N; n++) {

			double min = Double.MAX_VALUE;
			int minStar = 0;

			for (int i = 0; i < N; i++) {
				if (!v[i] && min > minDist[i]) {
					min = minDist[i];
					minStar = i;
				}
			}

			v[minStar] = true;
			sum += min;

			for (int i = 0; i < N; i++) {
				if (!v[i] && minDist[i] > distance[minStar][i])
					minDist[i] = distance[minStar][i];
			}

		}

		System.out.printf("%.2f", sum);

	} // end of main

} // end of class
