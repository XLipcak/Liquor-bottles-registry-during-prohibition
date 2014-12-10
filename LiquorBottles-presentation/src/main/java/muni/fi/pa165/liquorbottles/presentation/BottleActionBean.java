package muni.fi.pa165.liquorbottles.presentation;

import java.util.Date;
import java.util.List;
import static muni.fi.pa165.liquorbottles.presentation.BaseActionBean.escapeHTML;
import muni.fi.pa165.liquorbottles.presentation.utils.ChartCreator;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.api.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import muni.fi.pa165.liquorbottles.api.services.BottleTypeService;
import muni.fi.pa165.liquorbottles.api.services.ProducerService;

import muni.fi.pa165.liquorbottles.api.services.StoreService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@UrlBinding("/bottle.action")
public class BottleActionBean extends BaseActionBean implements ValidationErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreActionBean.class);

    @SpringBean
    protected BottleService bottleService;

    @SpringBean
    protected StoreService storeService;

    @SpringBean
    protected BottleTypeService bottleTypeService;

    @SpringBean
    protected ProducerService producerService;

    private List<BottleDTO> bottleList;
    private long storeID;
    private long bottleTypeID;
    private long producerID;

    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "batchNumber", required = true),
        @Validate(on = {"add", "save"}, field = "stamp", required = true),
        @Validate(on = {"add", "save"}, field = "dateOfBirth", required = true)
    })
    private BottleDTO bottle;

    private ToxicityDTO toxicitySelect = ToxicityDTO.TOXIC;
    @ValidateNestedProperties(value = {
        @Validate(on = {"filter"}, field = "batchNumber")})
    private long batchNumber;
    @ValidateNestedProperties(value = {
        @Validate(on = {"filter"}, field = "stamp")})
    private long stamp;
    @ValidateNestedProperties(value = {
        @Validate(on = {"filter"}, field = "dateFrom")})
    private Date dateFrom;
    @ValidateNestedProperties(value = {
        @Validate(on = {"filter"}, field = "dateTo")})
    private Date dateTo;

    private List<BottleTypeDTO> bottleTypeList;
    private List<StoreDTO> storeList;
    private List<ProducerDTO> producerList;

    @DefaultHandler
    public Resolution list() {
        LOGGER.debug("list()");
        ChartCreator.createPieChart(bottleService.findByToxicity(ToxicityDTO.TOXIC).size(),
                bottleService.findByToxicity(ToxicityDTO.UNCHECKED).size(),
                bottleService.findByToxicity(ToxicityDTO.NON_TOXIC).size());

        bottleList = bottleService.findAll();
        bottleTypeList = bottleTypeService.findAll();
        storeList = storeService.findAll();
        producerList = producerService.findAll();
        return new ForwardResolution("/bottle/list.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        bottleList = bottleService.findAll();
        bottleTypeList = bottleTypeService.findAll();
        storeList = storeService.findAll();
        return null;
    }

    public Resolution add() {
        LOGGER.debug("add() bottle={}", bottle);
        if (getStoreID() <= 0) {
            getContext().getMessages().add(new LocalizableMessage("bottle.add.store.error.message"));
        } else {
            if (getBottleTypeID() <= 0) {
                getContext().getMessages().add(new LocalizableMessage("bottle.add.btype.error.message"));
            } else {

                try {
                    bottle.setStore(storeService.findById(storeID));
                    bottle.setBottleType(bottleTypeService.findById(bottleTypeID));
                    bottle.setToxicity(toxicitySelect);

                    bottleService.insertBottle(bottle);
                    getContext().getMessages().add(new LocalizableMessage("bottle.add.message",
                            escapeHTML(bottleTypeService.findById(bottle.getBottleType().getId()).getName()),
                            escapeHTML(String.valueOf(bottle.getStamp()))));
                } catch (Exception ex) {
                    getContext().getMessages().add(new LocalizableMessage("bottle.stamp.error.message", escapeHTML(Long.toString(bottle.getStamp()))));
                }
            }
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadBottleFromDatabase() {
        String ids = getContext().getRequest().getParameter("bottle.id");
        if (ids == null) {
            return;
        }
        bottle = bottleService.findById(Long.parseLong(ids));
    }

    public Resolution edit() {
        LOGGER.debug("edit() bottle={}", bottle);
        bottleTypeList = bottleTypeService.findAll();
        storeList = storeService.findAll();
        toxicitySelect = bottle.getToxicity();
        return new ForwardResolution("/bottle/edit.jsp");
    }

    public Resolution filter() {
        if (bottleTypeID <= 0) {
            bottleTypeID = -1;
        }
        if (storeID <= 0) {
            storeID = -1;
        }
        if (producerID <= 0) {
            producerID = -1;
        }
        if (batchNumber <= 0) {
            batchNumber = -1;
        }
        if (stamp <= 0) {
            stamp = -1;
        }
        bottleList = bottleService.findByFilter(bottleTypeID, producerID, storeID, toxicitySelect, dateFrom, dateTo, batchNumber, stamp);
        bottleTypeList = bottleTypeService.findAll();
        storeList = storeService.findAll();
        producerList = producerService.findAll();
        return new ForwardResolution("/bottle/list.jsp");
    }

    public Resolution save() {
        LOGGER.debug("save() bottle={}", bottle);
        bottle.setStore(storeService.findById(storeID));
        bottle.setBottleType(bottleTypeService.findById(bottleTypeID));
        bottle.setToxicity(toxicitySelect);

        try {
            bottleService.updateBottle(bottle);
        } catch (Exception ex) {
            getContext().getMessages().add(new LocalizableMessage("bottle.stamp.error.message", escapeHTML(Long.toString(bottle.getStamp()))));
        }

        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution delete() {
        LOGGER.debug("delete({})", bottle.getId());
        //only id is filled by the form
        bottle = bottleService.findById(bottle.getId());
        bottleService.deleteBottle(bottle);
        getContext().getMessages().add(new LocalizableMessage("bottle.delete.message", escapeHTML(String.valueOf(bottle.getStamp()))));
        return new RedirectResolution(this.getClass(), "list");
    }

    public BottleService getBottleService() {
        return bottleService;
    }

    public void setBottleService(BottleService bottleService) {
        this.bottleService = bottleService;
    }

    public StoreService getStoreService() {
        return storeService;
    }

    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    public BottleTypeService getBottleTypeService() {
        return bottleTypeService;
    }

    public void setBottleTypeService(BottleTypeService bottleTypeService) {
        this.bottleTypeService = bottleTypeService;
    }

    public ToxicityDTO getToxicitySelect() {
        return toxicitySelect;
    }

    public void setToxicitySelect(ToxicityDTO toxicitySelect) {
        this.toxicitySelect = toxicitySelect;
    }

    public List<BottleTypeDTO> getBottleTypeList() {
        return bottleTypeList;
    }

    public void setBottleTypeList(List<BottleTypeDTO> bottleTypeList) {
        this.bottleTypeList = bottleTypeList;
    }

    public List<StoreDTO> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreDTO> storeList) {
        this.storeList = storeList;
    }

    public List<BottleDTO> getBottleList() {
        return bottleList;
    }

    public void setBottleList(List<BottleDTO> bottleList) {
        this.bottleList = bottleList;
    }

    public BottleDTO getBottle() {
        return bottle;
    }

    public void setBottle(BottleDTO bottle) {
        this.bottle = bottle;
    }

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public long getBottleTypeID() {
        return bottleTypeID;
    }

    public void setBottleTypeID(long bottleTypeID) {
        this.bottleTypeID = bottleTypeID;
    }

    public List<ProducerDTO> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<ProducerDTO> producerList) {
        this.producerList = producerList;
    }

    public long getProducerID() {
        return producerID;
    }

    public void setProducerID(long producerID) {
        this.producerID = producerID;
    }

    public long getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(long batchNumber) {
        this.batchNumber = batchNumber;
    }

    public long getStamp() {
        return stamp;
    }

    public void setStamp(long stamp) {
        this.stamp = stamp;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

}
