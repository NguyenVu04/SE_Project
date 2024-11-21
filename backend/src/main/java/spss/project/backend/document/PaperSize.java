package spss.project.backend.document;

/**
 * An enumeration representing the different paper sizes that are supported.
 * 
 * The supported paper sizes are A3, A4, A5, A6, and Letter. Each of the
 * enumeration constants has a value associated with it, which is the cost of
 * printing a document of that size in dollars.
 */
public enum PaperSize {
    /**
     * A3 paper size (297mm x 420mm)
     * 
     * The cost of printing a document of this size is $2.
     */
    A3(2),
    /**
     * A4 paper size (210mm x 297mm)
     * 
     * The cost of printing a document of this size is $1.
     */
    A4(1),
    /**
     * A5 paper size (148mm x 210mm)
     * 
     * The cost of printing a document of this size is $0.5.
     */
    A5(0.5),
    /**
     * A6 paper size (105mm x 148mm)
     * 
     * The cost of printing a document of this size is $0.25.
     */
    A6(0.25),
    /**
     * Letter paper size (216mm x 279mm)
     * 
     * The cost of printing a document of this size is $0.75.
     */
    Letter(0.75);

    /**
     * The cost of printing a document of this size in dollars.
     */
    private final double value;

    /**
     * Constructor.
     * 
     * @param value the cost of printing a document of this size in dollars
     */
    private PaperSize(double value) {
        this.value = value;
    }

    /**
     * Gets the cost of printing a document of this size in dollars.
     * 
     * @return the cost of printing a document of this size in dollars
     */
    public double getValue() {
        return value;
    }
}
