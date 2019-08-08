/**
 * Author: dbautistav
 * Contributor: Omar García
 */

package com.bbva.intranet.utilities.thymeleaf.processors;

//import com.bancomer.intranet.prestamo.vo.PagerData;
//import com.google.gson.Gson;
//import org.thymeleaf.Arguments;
//import org.thymeleaf.dom.Element;
//import org.thymeleaf.dom.Node;
//import org.thymeleaf.dom.Text;
//import org.thymeleaf.processor.element.AbstractMarkupSubstitutionElementProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a (thymeleaf) dialect processor which creates a pager for POST (HttpRequest method) calls.
 */
public class PagerPostProcessor /*extends AbstractMarkupSubstitutionElementProcessor*/ {

//    /**
//     * Element tag
//     */
//    public PagerPostProcessor() {
//        super("pagerp");
//    }
//
//    /**
//     * This method sets the tag's precedence
//     * @return - The tags precedence.
//     */
//    @Override
//    public int getPrecedence() {
//        return 12000;
//    }
//
//    /**
//     * This method substitutes the custom tag with the pager's DOM structure.
//     * @param arguments - The element where we can obtain the application context
//     *                  to retrieve some webapp component values.
//     * @param element - The element where we can obtain the custom tag arguments.
//     * @return - The DOM structure to be substituted by the custom tag.
//     */
//    @Override
//    protected List<Node> getMarkupSubstitutes(
//            final Arguments arguments, final Element element) {
//
//        /*
//         * Reading the attributes from the tag.
//         */
//        String pagerPostJson = element.getAttributeValue("pagerPostJson");
//        Gson gson = new Gson();
//        PagerData pagerVO = gson.fromJson(pagerPostJson, PagerData.class);
//
//        final Element mainContainer;
//
//        if (pagerVO.getMaxPages() <= 1) {
//            mainContainer = new Element("span");
//        } else {
//            // Always shows 'pagesDisplayed' page numbers in the pager
//            int firstPage = pagerVO.getCurrentPage() - pagerVO.getPagesBeforeMiddle();
//            if (firstPage <= 0) {
//                firstPage = 1;
//            }
//
//            int lastPage = firstPage + pagerVO.getPagesDisplayed() - 1;
//            if (lastPage > pagerVO.getMaxPages()) {
//                lastPage = pagerVO.getMaxPages();
//                if (firstPage != (lastPage - pagerVO.getPagesDisplayed())) {
//                    firstPage = lastPage - pagerVO.getPagesDisplayed() + 1;
//                    if (firstPage <= 0) {
//                        firstPage = 1;
//                    }
//                }
//            }
//
//           /*
//            * Creating the DOM structure that will be substituting our custom tag.
//            * The menu will be shown inside other tags, and so these must be created first.
//            */
//            mainContainer = new Element("ul");
//            mainContainer.setAttribute("class", "pagination");
//
//            //  First element
//            final Element liFirstElement = new Element("li");
//            if (pagerVO.getCurrentPage() != 1) {
//                liFirstElement.setAttribute("class", "first");
//            } else {
//                liFirstElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liFirstElement);
//
//            final Element buttonFirstElement = new Element("button");
//            buttonFirstElement.setAttribute("class", "btn");
//            buttonFirstElement.setAttribute("type", "submit");
//            buttonFirstElement.setAttribute("name", "page");
//            buttonFirstElement.setAttribute("value", String.valueOf(pagerVO.getFirstPage()));
//            liFirstElement.addChild(buttonFirstElement);
//
////            final Text firstLbl = new Text("|◄");
////            final Text firstLbl = new Text("Primero");
//            final Text firstLbl = new Text(pagerVO.getFirstPageLabel());
//            buttonFirstElement.addChild(firstLbl);
//
//            // Prev element
//            final Element liPrevElement = new Element("li");
//            if (pagerVO.hasPrev()) {
//                liPrevElement.setAttribute("class", "previous");
//            } else {
//                liPrevElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liPrevElement);
//
//            final Element buttonPrevElement = new Element("button");
//            buttonPrevElement.setAttribute("class", "btn");
//            buttonPrevElement.setAttribute("type", "submit");
//            buttonPrevElement.setAttribute("name", "page");
//            if (pagerVO.hasPrev()) {
//                buttonPrevElement.setAttribute("value", String.valueOf(pagerVO.getCurrentPage() - 1));
//            } else {
//                buttonPrevElement.setAttribute("value", String.valueOf(pagerVO.getCurrentPage()));
//            }
//            liPrevElement.addChild(buttonPrevElement);
//
////            final Text prevLbl = new Text("◄");
////            final Text prevLbl = new Text("Anterior");
//            final Text prevLbl = new Text(pagerVO.getPreviousLabel());
//            buttonPrevElement.addChild(prevLbl);
//
//            // Intermediate elements
//            for (int i = firstPage; i <= lastPage; i++) {
//                final Element liInterElement = new Element("li");
//                if (pagerVO.getCurrentPage() == i) {
//                    liInterElement.setAttribute("class", "active");
//                } else {
//                    liInterElement.setAttribute("class", "inactive");
//                }
//                mainContainer.addChild(liInterElement);
//
//                final Element buttonInterElement = new Element("button");
//                buttonInterElement.setAttribute("class", "btn");
//                buttonInterElement.setAttribute("type", "submit");
//                buttonInterElement.setAttribute("name", "page");
//                buttonInterElement.setAttribute("value", String.valueOf(i));
//                liInterElement.addChild(buttonInterElement);
//
//                // If intermediate element is the current page...
//                if (i == pagerVO.getCurrentPage()) {
//                    final Element bInterElement = new Element("em");
//                    buttonInterElement.addChild(bInterElement);
//
//                    final Text interLbl = new Text(String.format("%s", String.valueOf(i)));
//                    bInterElement.addChild(interLbl);
//                } else {
//                    final Text interLbl = new Text(String.valueOf(i));
//                    buttonInterElement.addChild(interLbl);
//                }
//            }
//
//            // Next element
//            final Element liNextElement = new Element("li");
//            if (pagerVO.hasNext()) {
//                liNextElement.setAttribute("class", "next");
//            } else {
//                liNextElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liNextElement);
//
//            final Element buttonNextElement = new Element("button");
//            buttonNextElement.setAttribute("class", "btn");
//            buttonNextElement.setAttribute("type", "submit");
//            buttonNextElement.setAttribute("name", "page");
//            if (pagerVO.hasNext()) {
//                buttonNextElement.setAttribute("value", String.valueOf(pagerVO.getCurrentPage() + 1));
//            } else {
//                buttonNextElement.setAttribute("value", String.valueOf(pagerVO.getCurrentPage()));
//            }
//            liNextElement.addChild(buttonNextElement);
//
////            final Text nextLbl = new Text("►");
////            final Text nextLbl = new Text("Siguiente");
//            final Text nextLbl = new Text(pagerVO.getNextLabel());
//            buttonNextElement.addChild(nextLbl);
//
//            // Last element
//            final Element liLastElement = new Element("li");
//            if (pagerVO.getCurrentPage() != pagerVO.getMaxPages()) {
//                liLastElement.setAttribute("class", "last");
//            } else {
//                liLastElement.setAttribute("class", "off");
//            }
//            mainContainer.addChild(liLastElement);
//
//            final Element buttonLastElement = new Element("button");
//            buttonLastElement.setAttribute("class", "btn");
//            buttonLastElement.setAttribute("type", "submit");
//            buttonLastElement.setAttribute("name", "page");
//            buttonLastElement.setAttribute("value", String.valueOf(pagerVO.getMaxPages()));
//            liLastElement.addChild(buttonLastElement);
//
////            final Text lastLbl = new Text("►|");
////            final Text lastLbl = new Text("Último");
//            final Text lastLbl = new Text(pagerVO.getLastPageLabel());
//            buttonLastElement.addChild(lastLbl);
//        }
//
//        /*
//         * The abstract IAttrProcessor implementation we are using defines
//         * that a list of nodes will be returned, and that these nodes
//         * will substitute the tag we are processing.
//         */
//        final List<Node> nodes = new ArrayList<Node>();
//        nodes.add(mainContainer);
//
//        return nodes;
//
//    }
}
