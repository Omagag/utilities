package com.bbva.intranet.utilities.thymeleaf.dialects;

import com.bbva.intranet.utilities.thymeleaf.processors.PagerPostProcessor;
import com.bbva.intranet.utilities.thymeleaf.processors.PagerProcessor;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * Utilities and components dialect
 *
 * This class declares the related stuff for the proprietary dialect,
 *  which includes the menus, pagers and cookie utility.
 */
public class DreamTeamDialect extends AbstractProcessorDialect {

    protected DreamTeamDialect() {
        super("Paginator Dialect", "dt", 1000);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new PagerProcessor(dialectPrefix));
//        processors.add(new PagerPostProcessor());
        return processors;
    }

}
