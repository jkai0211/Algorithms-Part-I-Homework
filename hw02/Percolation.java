import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF wqu;
    private boolean[] sites;
    private int size;
    private int numberOfSite;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {

            throw new IllegalArgumentException();
        }
        else {
            openSites = 0;
            size = n;
            numberOfSite = n * n + 2;
            wqu = new WeightedQuickUnionUF(numberOfSite);
            sites = new boolean[numberOfSite];
            for (int i = 0; i < sites.length; i++) {
                sites[i] = false;
            }
            // union bottom node with last column from grid
            // union up node with first column from grid
            for (int node = 0; node < n; node++) {
                wqu.union(0, node + 1);
                wqu.union(numberOfSite - 1,
                          numberOfSite - 2 - node); // numberOfSite-1 is the bottom,
            }

        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException();
        }
        else {
            int index = getIndex(row, col);
            if (checkIsOpen(index))
                return;

            sites[index] = true;
            openSites++;

            // left check and union
            if (col != 1 && checkIsOpen(index - 1)) {
                wqu.union(index, index - 1);
            }

            // right check and union
            if (col != size && checkIsOpen(index + 1)) {
                wqu.union(index, index + 1);
            }

            // up check and union
            if (row != 1 && checkIsOpen(index - size)) {
                wqu.union(index, index - size);
            }

            // bottom check and union
            if (row != size && checkIsOpen(index + size)) {
                wqu.union(index, index + size);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException();
        }
        else {
            int index = getIndex(row, col);
            return sites[index];
        }
    }

    // is the site (row, col) full?

    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException();
        }
        else {
            int index = getIndex(row, col);
            return isOpen(row, col) && wqu.find(index) == wqu.find(0);
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (size == 1)
            return isOpen(1, 1);
        return wqu.find(numberOfSite - 1) == wqu.find(0);
    }

    private boolean checkArrayIdx(int i) {
        return i >= 0 && i < sites.length;
    }

    private boolean checkIsOpen(int idx) {
        return checkArrayIdx(idx) && sites[idx];
    }

    private int getIndex(int row, int col) {
        return (row - 1) * size + (col - 1) + 1;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        p.open(0, 5);
    }
}
