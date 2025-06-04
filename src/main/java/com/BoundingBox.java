package com;

import java.util.*;


public class BoundingBox {
    static int numRows, numCols;
    static char[][] matrix;
    static boolean[][] seen;

    static class Rectangle {
        int top, left, bottom, right;
        int size;

        Rectangle(int top, int left, int bottom, int right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
            this.size = (bottom - top + 1) * (right - left + 1);
        }

        boolean intersects(boolean[][] marked) {
            for (int r = top; r <= bottom; r++) {
                for (int c = left; c <= right; c++) {
                    if (marked[r][c]) return true;
                }
            }
            return false;
        }

        void fill(boolean[][] marked) {
            for (int r = top; r <= bottom; r++) {
                for (int c = left; c <= right; c++) {
                    marked[r][c] = true;
                }
            }
        }

        public String toString() {
            return "(" + (top + 1) + "," + (left + 1) + ")(" + (bottom + 1) + "," + (right + 1) + ")";
        }
    }

    static final int[] dirR = {-1, 1, 0, 0};
    static final int[] dirC = {0, 0, -1, 1};

    private static Rectangle exploreRegion(int sr, int sc) {
        Queue<int[]> frontier = new LinkedList<>();
        frontier.offer(new int[]{sr, sc});
        seen[sr][sc] = true;

        int minR = sr, maxR = sr, minC = sc, maxC = sc;

        while (!frontier.isEmpty()) {
            int[] curr = frontier.poll();
            int r = curr[0], c = curr[1];

            for (int i = 0; i < 4; i++) {
                int newR = r + dirR[i];
                int newC = c + dirC[i];

                if (newR >= 0 && newR < numRows && newC >= 0 && newC < numCols &&
                    !seen[newR][newC] && matrix[newR][newC] == '*') {
                    
                    seen[newR][newC] = true;
                    frontier.offer(new int[]{newR, newC});
                    minR = Math.min(minR, newR);
                    maxR = Math.max(maxR, newR);
                    minC = Math.min(minC, newC);
                    maxC = Math.max(maxC, newC);
                }
            }
        }

        return new Rectangle(minR, minC, maxR, maxC);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        if (lines.isEmpty()) return;

        numRows = lines.size();
        numCols = lines.get(0).length();
        matrix = new char[numRows][numCols];
        seen = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            matrix[i] = lines.get(i).toCharArray();
        }

        List<Rectangle> regions = new ArrayList<>();
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (!seen[r][c] && matrix[r][c] == '*') {
                    regions.add(exploreRegion(r, c));
                }
            }
        }

        boolean[][] used = new boolean[numRows][numCols];
        Rectangle maxRect = null;

        for (Rectangle rect : regions) {
            if (!rect.intersects(used)) {
                if (maxRect == null || rect.size > maxRect.size) {
                    maxRect = rect;
                }
                rect.fill(used);
            }
        }

        if (maxRect != null) {
            System.out.println(maxRect);
            System.exit(0);
        } else {
            System.exit(1);
        }
    }
}
