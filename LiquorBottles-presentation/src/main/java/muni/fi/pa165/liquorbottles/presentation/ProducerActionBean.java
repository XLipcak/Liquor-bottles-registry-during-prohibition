package muni.fi.pa165.liquorbottles.presentation;

import java.util.List;
import static muni.fi.pa165.liquorbottles.presentation.BaseActionBean.escapeHTML;
import muni.fi.pa165.liquorbottles.service.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.service.services.ProducerService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@UrlBinding("/producer.action")
public class ProducerActionBean extends BaseActionBean implements ValidationErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerActionBean.class);

    @SpringBean
    protected ProducerService producerService;

    private List<ProducerDTO> producerList;

    public List<ProducerDTO> getProducerList() {
        return producerList;
    }

    public ProducerActionBean() {
        BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);
    }

    @DefaultHandler
    public Resolution list() {
        LOGGER.debug("list()");
        producerList = producerService.findAll();
        return new ForwardResolution("/producer/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        producerList = producerService.findAll();
        return null;
    }

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "address", required = true),
        @Validate(on = {"add", "save"}, field = "username", required = true),
        @Validate(on = {"add", "save"}, field = "password", required = true)
    })

    private ProducerDTO producer;

    public ProducerDTO getProducer() {
        return producer;
    }

    public void setProducer(ProducerDTO producer) {
        this.producer = producer;
    }

    public Resolution add() {
        LOGGER.debug("add() producer={}", producer);
        producerService.insertProducer(producer);
        getContext().getMessages().add(new LocalizableMessage("producer.add.message", escapeHTML(producer.getName()), escapeHTML(producer.getAddress())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadProducerFromDatabase() {
        String ids = getContext().getRequest().getParameter("producer.id");
        if (ids == null) {
            return;
        }
        producer = producerService.findById(Long.parseLong(ids));
    }

    public Resolution edit() {
        LOGGER.debug("edit() producer={}", producer);
        return new ForwardResolution("/producer/edit.jsp");
    }

    
    public Resolution save() {
        LOGGER.debug("save() producer={}", producer);
        producerService.updateProducer(producer);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        LOGGER.debug("delete({})", producer.getId());
        //only id is filled by the form
        producer = producerService.findById(producer.getId());
        producerService.deleteProducer(producer);
        getContext().getMessages().add(new LocalizableMessage("producer.delete.message", escapeHTML(producer.getName()), escapeHTML(producer.getAddress())));
        return new RedirectResolution(this.getClass(), "list");
    }
}
