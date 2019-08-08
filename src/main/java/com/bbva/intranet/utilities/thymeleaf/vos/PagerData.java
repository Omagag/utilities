/**
 * author = dbautistav
 */

package com.bbva.intranet.utilities.thymeleaf.vos;

/**
 * This class is a pager value object.
 *  It is intended for carrying some values related to the pager from the controller to the view.
 */
public class PagerData {

    private String uri;

    private int firstPage;
    private int currentPage;
    private int pagesBeforeMiddle;
    private int pagesDisplayed;
    private int maxPages;

    private String firstPageLabel;
    private String previousLabel;
    private String nextLabel;
    private String lastPageLabel;

    public static PagerData createInstance(final String uri, final int currentPage, final int elementsSize,
                                           String firstPageLabel, String previousLabel, String nextLabel, String lastPageLabel) {
        final int firstPage = 1;
        final int pageSize = 10;
        final int pagesBeforeMiddle = 2;
        final int pagesDisplayed = 5;
        final int pages =  pages(elementsSize, pageSize);
//        final String firstString = "Primero";
//        final String previousString = "Anterior";
//        final String nextString = "Siguiente";
//        final String lastString = "Último";
        PagerData pagerData = new PagerData();

        pagerData.setCurrentPage(currentPage);
        pagerData.setFirstPage(firstPage);
//        pagerData.setHasPrev(page > firstPage);
//        pagerData.setHasNext(page < pages);
        pagerData.setMaxPages(pages);
        pagerData.setPagesBeforeMiddle(pagesBeforeMiddle);
        pagerData.setPagesDisplayed(pagesDisplayed);
        pagerData.setUri(uri);
        pagerData.firstPageLabel = firstPageLabel;
        pagerData.previousLabel = previousLabel;
        pagerData.nextLabel = nextLabel;
        pagerData.lastPageLabel = lastPageLabel;

        return pagerData;
    }

    public static PagerData createInstance(final String uri, final int currentPage, final int pageSize, final int elementsSize,
                                           String firstPageLabel, String previousLabel, String nextLabel, String lastPageLabel) {
//        final int firstPage = 1;
//        final int pagesBeforeMiddle = 2;
//        final int pagesDisplayed = 5;
        final int pages =  pages(elementsSize, pageSize);
//        final String firstString = "Primero";
//        final String previousString = "Anterior";
//        final String nextString = "Siguiente";
//        final String lastString = "Último";
//        PagerData pagerData = new PagerData();
        PagerData pagerData = createInstance(uri, currentPage, elementsSize, firstPageLabel, previousLabel, nextLabel, lastPageLabel);

//        pagerData.setCurrentPage(page);
//        pagerData.setFirstPage(firstPage);
////        pagerData.setHasPrev(page > firstPage);
////        pagerData.setHasNext(page < pages);
        pagerData.setMaxPages(pages);
//        pagerData.setPagesBeforeMiddle(pagesBeforeMiddle);
//        pagerData.setPagesDisplayed(pagesDisplayed);
//        pagerData.setUri(uri);
//        pagerData.setFirstPageLabel(firstString);
//        pagerData.setPreviousLabel(previousString);
//        pagerData.setNextLabel(nextString);
//        pagerData.setLastPageLabel(lastString);

        return pagerData;
    }

    public static PagerData createInstance(final String uri, final int firstPage, final int currentPage, final int pageSize, final int elementsSize,
                                           String firstPageLabel, String previousLabel, String nextLabel, String lastPageLabel) {
//        final int pagesBeforeMiddle = 2;
//        final int pagesDisplayed = 5;
//        final int pages =  pages(elementsSize, pageSize);
//        final String firstString = "Primero";
//        final String previousString = "Anterior";
//        final String nextString = "Siguiente";
//        final String lastString = "Último";
//        PagerData pagerData = new PagerData();
        PagerData pagerData = createInstance(uri, currentPage, pageSize, elementsSize, firstPageLabel, previousLabel, nextLabel, lastPageLabel);

//        pagerData.setCurrentPage(page);
        pagerData.setFirstPage(firstPage);
////        pagerData.setHasPrev(page > firstPage);
////        pagerData.setHasNext(page < pages);
//        pagerData.setMaxPages(pages);
//        pagerData.setPagesBeforeMiddle(pagesBeforeMiddle);
//        pagerData.setPagesDisplayed(pagesDisplayed);
//        pagerData.setUri(uri);
//        pagerData.setFirstPageLabel(firstString);
//        pagerData.setPreviousLabel(previousString);
//        pagerData.setNextLabel(nextString);
//        pagerData.setLastPageLabel(lastString);

        return pagerData;
    }

    public static PagerData createInstance(final String uri, final int firstPage, final int currentPage, final int pagesBeforeMiddle,
                                           final int pagesDisplayed, final int pageSize, final int elementsSize,
                                           String firstPageLabel, String previousLabel, String nextLabel, String lastPageLabel) {
//        final int pages =  pages(elementsSize, pageSize);
//        final String firstString = "Primero";
//        final String previousString = "Anterior";
//        final String nextString = "Siguiente";
//        final String lastString = "Último";
//        PagerData pagerData = new PagerData();
        PagerData pagerData = createInstance(uri, firstPage, currentPage, pageSize, elementsSize, firstPageLabel, previousLabel, nextLabel, lastPageLabel);

//        pagerData.setCurrentPage(page);
//        pagerData.setFirstPage(firstPage);
//        pagerData.setHasPrev(page > firstPage);
//        pagerData.setHasNext(page < pages);
//        pagerData.setMaxPages(pages);
        pagerData.setPagesBeforeMiddle(pagesBeforeMiddle);
        pagerData.setPagesDisplayed(pagesDisplayed);
//        pagerData.setUri(uri);
//        pagerData.setFirstPageLabel(firstString);
//        pagerData.setPreviousLabel(previousString);
//        pagerData.setNextLabel(nextString);
//        pagerData.setLastPageLabel(lastString);

        return pagerData;
    }

//    public static PagerData createInstance(final String uri, final int firstPage, final int page, final int pagesBeforeMiddle, final int pagesDisplayed, final int page_size, final int elements_size, final String firstString, final String previousString, final String nextString, final String lastString) {
//        final int pages =  pages(elements_size, page_size);
//        PagerData pagerData = new PagerData();
//
//        pagerData.setCurrentPage(page);
//        pagerData.setFirstPage(firstPage);
////        pagerData.setHasPrev(page > firstPage);
////        pagerData.setHasNext(page < pages);
//        pagerData.setMaxPages(pages);
//        pagerData.setPagesBeforeMiddle(pagesBeforeMiddle);
//        pagerData.setPagesDisplayed(pagesDisplayed);
//        pagerData.setUri(uri);
//        pagerData.setFirstPageLabel(firstString);
//        pagerData.setPreviousLabel(previousString);
//        pagerData.setNextLabel(nextString);
//        pagerData.setLastPageLabel(lastString);
//
//        return pagerData;
//    }

    public static int pages(final int elementsSize, final int pageSize) {
        return (pageSize != 0) ? (((elementsSize % pageSize) == 0) ? (int)Math.ceil(elementsSize / pageSize) : 1 + (int)Math.ceil(elementsSize / pageSize)) : 0;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPagesBeforeMiddle() {
        return pagesBeforeMiddle;
    }

    public void setPagesBeforeMiddle(int pagesBeforeMiddle) {
        this.pagesBeforeMiddle = pagesBeforeMiddle;
    }

    public int getPagesDisplayed() {
        return pagesDisplayed;
    }

    public void setPagesDisplayed(int pagesDisplayed) {
        this.pagesDisplayed = pagesDisplayed;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

//    public boolean isHasPrev() {
//        return hasPrev;
//    }
//
//    public void setHasPrev(boolean hasPrev) {
//        this.hasPrev = hasPrev;
//    }
//
//    public boolean isHasNext() {
//        return hasNext;
//    }
//
//    public void setHasNext(boolean hasNext) {
//        this.hasNext = hasNext;
//    }

//    public String getFirstPageLabel() {
//        return firstPageLabel;
//    }
//
//    public void setFirstPageLabel(String firstPageLabel) {
//        this.firstPageLabel = firstPageLabel;
//    }
//
//    public String getPreviousLabel() {
//        return previousLabel;
//    }
//
//    public void setPreviousLabel(String previousLabel) {
//        this.previousLabel = previousLabel;
//    }
//
//    public String getNextLabel() {
//        return nextLabel;
//    }
//
//    public void setNextLabel(String nextLabel) {
//        this.nextLabel = nextLabel;
//    }
//
//    public String getLastPageLabel() {
//        return lastPageLabel;
//    }
//
//    public void setLastPageLabel(String lastPageLabel) {
//        this.lastPageLabel = lastPageLabel;
//    }

    public boolean hasPreviousPage() {
        return this.currentPage > this.firstPage;
    }

    public boolean hasNextPage() {
        return this.currentPage < this.maxPages;
    }
}
