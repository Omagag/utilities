/**
 * Author: dbautistav
 * Contributor: omaroman
 */

package com.bbva.intranet.utilities.thymeleaf.processors;

import com.bbva.intranet.utilities.thymeleaf.vos.PagerData;
import com.google.gson.Gson;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.*;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

/**
 * This class is a (thymeleaf) dialect processor which creates a pager for GET (HttpRequest method) calls.
 */
public class PagerProcessor extends AbstractElementTagProcessor {

    private static final String TAG_NAME = "pager";
    private static final int PRECEDENCE = 12000;

    private int currentPage;
    private int firstPage;
    private int maxPages;

    /**
     * Element tag
     */
    public PagerProcessor(final String dialectPrefix) {
        super(
                TemplateMode.HTML,
                dialectPrefix,
                TAG_NAME,
                true,
                null,
                false,
                PRECEDENCE);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        /*
         * Reading the attributes from the tag.
         */
//        String pagerJson = tag.getAttributeValue("pagerJson");
//        Gson gson = new Gson();
//        PagerData pagerData = gson.fromJson(pagerJson, PagerData.class);

//        final IProcessableElementTag mainContainer;
        String uri;
        if (tag.getAttributeValue("uri") != null) {
            uri = tag.getAttributeValue("uri");
        } else {
            throw new IllegalArgumentException("uri value is not valid.");
        }
        if (tag.getAttributeValue("firstPage") != null) {
            this.firstPage = Integer.valueOf(tag.getAttributeValue("firstPage"));
        } else {
            throw new IllegalArgumentException("firstPage value is not valid.");
        }
        if (tag.getAttributeValue("currentPage") != null) {
            this.currentPage = Integer.valueOf(tag.getAttributeValue("currentPage"));
        } else {
            throw new IllegalArgumentException("currentPage value is not valid.");
        }
        if (tag.getAttributeValue("maxPages") != null) {
            this.maxPages = Integer.valueOf(tag.getAttributeValue("maxPages"));
        } else {
            throw new IllegalArgumentException("maxPages value is not valid.");
        }

        String firstPageLabel;
        if (tag.getAttributeValue("firstPageLabel") != null) {
            firstPageLabel = tag.getAttributeValue("firstPageLabel");
        } else {
            firstPageLabel = "First";
        }
        String previousLabel;
        if (tag.getAttributeValue("previousLabel") != null) {
            previousLabel = tag.getAttributeValue("previousLabel");
        } else {
            previousLabel = "Previous";
        }
        String nextLabel;
        if (tag.getAttributeValue("nextLabel") != null) {
            nextLabel = tag.getAttributeValue("nextLabel");
        } else {
            nextLabel = "Next";
        }
        String lastPageLabel;
        if (tag.getAttributeValue("lastPageLabel") != null) {
            lastPageLabel = tag.getAttributeValue("lastPageLabel");
        } else {
            lastPageLabel = "Last";
        }


        /*
         * Create the DOM structure that will be substituting our custom tag.
         * The headline will be shown inside a '<div>' tag, and so this must
         * be created first and then a Text node must be added to it.
         */
        final IModelFactory modelFactory = context.getModelFactory();

        final IModel model = modelFactory.createModel();



        String ANCHOR_TAG = "a";
        String LI_TAG = "li";
        String UL_TAG = "ul";
        String DIV_TAG = "div";


        model.add(modelFactory.createOpenElementTag(DIV_TAG, "class", "paginator"));
//        model.add(modelFactory.createText(HtmlEscape.escapeHtml5(headlineText)));




        // pagerData.getUri() = baseUri + actionUri
//        String firstURL = pagerData.hasPreviousPage() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getFirstPage()) : "#";
//        String previousURL = pagerData.hasPreviousPage() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getCurrentPage() - 1) : "#";
//        String nextURL = pagerData.hasNextPage() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getCurrentPage() + 1) : "#";
//        String lastURL = pagerData.hasNextPage() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getMaxPages()) : "#";

        String firstURL = this.hasPreviousPage() ? String.format("%s?page=%d", uri, this.firstPage) : "#";
        String previousURL = this.hasPreviousPage() ? String.format("%s?page=%d", uri, this.currentPage - 1) : "#";
        String nextURL = this.hasNextPage() ? String.format("%s?page=%d", uri, this.currentPage + 1) : "#";
        String lastURL = this.hasNextPage() ? String.format("%s?page=%d", uri, this.maxPages) : "#";





        model.add(modelFactory.createOpenElementTag(UL_TAG));

        // TODO: If the html is renewed

        // First Anchor
        model.add(modelFactory.createOpenElementTag(LI_TAG));
        IProcessableElementTag firstAnchor = modelFactory.createOpenElementTag(ANCHOR_TAG);
        model.add(firstAnchor);
        model.add(modelFactory.setAttribute(firstAnchor, "href", firstURL));
        model.add(modelFactory.createText(HtmlEscape.escapeHtml5(firstPageLabel)));
        model.add(modelFactory.createCloseElementTag(ANCHOR_TAG));
        model.add(modelFactory.createCloseElementTag(LI_TAG));

        // Previous Anchor
        model.add(modelFactory.createOpenElementTag(LI_TAG));
        IProcessableElementTag previousAnchor = modelFactory.createOpenElementTag(ANCHOR_TAG);
        model.add(previousAnchor);
        model.add(modelFactory.setAttribute(previousAnchor, "href", previousURL));
        model.add(modelFactory.createText(HtmlEscape.escapeHtml5(previousLabel)));
        model.add(modelFactory.createCloseElementTag(ANCHOR_TAG));
        model.add(modelFactory.createCloseElementTag(LI_TAG));


//        // Intermediate elements
//        for (int i = firstPage; i <= lastPage; i++) {
//            final Element liInterElement = new Element("li");
//            if (pagerData.getCurrentPage() == i) {
//                liInterElement.setAttribute("class", "active");
//            } else {
//                liInterElement.setAttribute("class", "inactive");
//            }
//            mainContainer.addChild(liInterElement);
//
//            final Element aInterElement = new Element("a");
//            if (i == pagerData.getCurrentPage()) {
//                aInterElement.setAttribute("href", "#");
//            } else {
//                aInterElement.setAttribute("href", String.format("%s?page=%d", pagerData.getUri(), i));
//            }
//            liInterElement.addChild(aInterElement);
//
//            // If intermediate element is the current page...
//            if (i == pagerData.getCurrentPage()) {
//                final Element bInterElement = new Element("em");
//                aInterElement.addChild(bInterElement);
//
//                final Text interLbl = new Text(String.format("%s", String.valueOf(i)));
//                bInterElement.addChild(interLbl);
//            } else {
//                final Text interLbl = new Text(String.valueOf(i));
//                aInterElement.addChild(interLbl);
//            }
//        }


        // Next Anchor
        model.add(modelFactory.createOpenElementTag(LI_TAG));
        IProcessableElementTag nextAnchor = modelFactory.createOpenElementTag(ANCHOR_TAG);
        model.add(nextAnchor);
        model.add(modelFactory.setAttribute(nextAnchor, "href", nextURL));
        model.add(modelFactory.createText(HtmlEscape.escapeHtml5(nextLabel)));
        model.add(modelFactory.createCloseElementTag(ANCHOR_TAG));
        model.add(modelFactory.createCloseElementTag(LI_TAG));

        // Last Anchor
        model.add(modelFactory.createOpenElementTag(LI_TAG));
        IProcessableElementTag lastAnchor = modelFactory.createOpenElementTag(ANCHOR_TAG);
        model.add(lastAnchor);
        model.add(modelFactory.setAttribute(lastAnchor, "href", lastURL));
        model.add(modelFactory.createText(HtmlEscape.escapeHtml5(lastPageLabel)));
        model.add(modelFactory.createCloseElementTag(ANCHOR_TAG));
        model.add(modelFactory.createCloseElementTag(LI_TAG));



//        // TODO: else by Javascript AJAX
//        model.add(modelFactory.createOpenElementTag("a"));
//        model.add(modelFactory.createCloseElementTag("a"));


        model.add(modelFactory.createCloseElementTag(UL_TAG));


        model.add(modelFactory.createCloseElementTag(DIV_TAG));



//        if (pagerData.getMaxPages() <= 1) {
//            mainContainer = new IProcessableElementTag("span");
//        } else {
//            // Always shows 'pagesDisplayed' page numbers in the pager
//            int firstPage = pagerData.getCurrentPage() - pagerData.getPagesBeforeMiddle();
//            if (firstPage <= 0) {
//                firstPage = 1;
//            }
//
//            int lastPage = firstPage + pagerData.getPagesDisplayed() - 1;
//            if (lastPage > pagerData.getMaxPages()) {
//                lastPage = pagerData.getMaxPages();
//                if (firstPage != (lastPage - pagerData.getPagesDisplayed())) {
//                    firstPage = lastPage - pagerData.getPagesDisplayed() + 1;
//                    if (firstPage <= 0) {
//                        firstPage = 1;
//                    }
//                }
//            }
//
////            // pagerData.getUri() = baseUri + actionUri
////            String firstURL = pagerData.hasPrev() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getFirstPage()) : "#";
////            String prevURL = pagerData.hasPrev() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getCurrentPage() - 1) : "#";
////            String nextURL = pagerData.hasNext() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getCurrentPage() + 1) : "#";
////            String lastURL = pagerData.hasNext() ? String.format("%s?page=%d", pagerData.getUri(), pagerData.getMaxPages()) : "#";
//
//            /*
//             * Creating the DOM structure that will be substituting our custom tag.
//             * The menu will be shown inside other tags, and so these must be created first.
//             */
//            mainContainer = new Element("ul");
//            mainContainer.setAttribute("class", "pagination");
//
//            //  First element
//            final Element liFirstElement = new Element("li");
//            if (pagerData.getCurrentPage() != 1) {
//                liFirstElement.setAttribute("class", "first");
//            } else {
//                liFirstElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liFirstElement);
//
//            final Element aFirstElement = new Element("a");
//            aFirstElement.setAttribute("href", firstURL);
//            liFirstElement.addChild(aFirstElement);
//
////            final Text firstLbl = new Text("|◄");
////            final Text firstLbl = new Text("Primero");
//            final Text firstLbl = new Text(pagerData.getFirstPageLabel());
//            aFirstElement.addChild(firstLbl);
//
//            // Prev element
//            final Element liPrevElement = new Element("li");
//            if (pagerData.hasPrev()) {
//                liPrevElement.setAttribute("class", "previous");
//            } else {
//                liPrevElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liPrevElement);
//
//            final Element aPrevElement = new Element("a");
//            aPrevElement.setAttribute("href", previousURL);
//            liPrevElement.addChild(aPrevElement);
//
////            final Text prevLbl = new Text("◄");
////            final Text prevLbl = new Text("Anterior");
//            final Text prevLbl = new Text(pagerData.getPreviousLabel());
//            aPrevElement.addChild(prevLbl);
//
//            // Intermediate elements
//            for (int i = firstPage; i <= lastPage; i++) {
//                final Element liInterElement = new Element("li");
//                if (pagerData.getCurrentPage() == i) {
//                    liInterElement.setAttribute("class", "active");
//                } else {
//                    liInterElement.setAttribute("class", "inactive");
//                }
//                mainContainer.addChild(liInterElement);
//
//                final Element aInterElement = new Element("a");
//                if (i == pagerData.getCurrentPage()) {
//                    aInterElement.setAttribute("href", "#");
//                } else {
//                    aInterElement.setAttribute("href", String.format("%s?page=%d", pagerData.getUri(), i));
//                }
//                liInterElement.addChild(aInterElement);
//
//                // If intermediate element is the current page...
//                if (i == pagerData.getCurrentPage()) {
//                    final Element bInterElement = new Element("em");
//                    aInterElement.addChild(bInterElement);
//
//                    final Text interLbl = new Text(String.format("%s", String.valueOf(i)));
//                    bInterElement.addChild(interLbl);
//                } else {
//                    final Text interLbl = new Text(String.valueOf(i));
//                    aInterElement.addChild(interLbl);
//                }
//            }
//
//            // Next element
//            final Element liNextElement = new Element("li");
//            if (pagerData.hasNext()) {
//                liNextElement.setAttribute("class", "next");
//            } else {
//                liNextElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liNextElement);
//
//            final Element aNextElement = new Element("a");
//            aNextElement.setAttribute("href", nextURL);
//            liNextElement.addChild(aNextElement);
//
////            final Text nextLbl = new Text("►");
////            final Text nextLbl = new Text("Siguiente");
//            final Text nextLbl = new Text(pagerData.getNextLabel());
//            aNextElement.addChild(nextLbl);
//
//            // Last element
//            final Element liLastElement = new Element("li");
//            if (pagerData.getCurrentPage() != pagerData.getMaxPages()) {
//                liLastElement.setAttribute("class", "last");
//            } else {
//                liLastElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liLastElement);
//
//            final Element aLastElement = new Element("a");
//            aLastElement.setAttribute("href", lastURL);
//            liLastElement.addChild(aLastElement);
//
////            final Text lastLbl = new Text("►|");
////            final Text lastLbl = new Text("Último");
//            final Text lastLbl = new Text(pagerData.getLastPageLabel());
//            aLastElement.addChild(lastLbl);
//        }
//
//        /*
//         * The abstract IAttrProcessor implementation we are using defines
//         * that a list of nodes will be returned, and that these nodes
//         * will substitute the tag we are processing.
//         */
//        final List<Node> nodes = new ArrayList<Node>();
//        nodes.add(mainContainer);





        /*
         * Instruct the engine to replace this entire element with the specified model.
         */
        structureHandler.replaceWith(model, false);


//        return nodes;
    }

    private boolean hasPreviousPage() {
        return this.currentPage > this.firstPage;
    }

    private boolean hasNextPage() {
        return this.currentPage < this.maxPages;
    }

}
