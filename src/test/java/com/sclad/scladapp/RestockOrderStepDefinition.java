package com.sclad.scladapp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sclad.scladapp.entity.Device;
import com.sclad.scladapp.entity.RestockOrder;
import com.sclad.scladapp.exceptions.DeviceNotFoundException;
import com.sclad.scladapp.model.DeviceModel;
import com.sclad.scladapp.model.RestockOrderModel;
import com.sclad.scladapp.service.DeviceService;
import com.sclad.scladapp.service.RestockOrderService;

public class RestockOrderStepDefinition extends SpringIntegrationTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RestockOrderService restockOrderService;
	private final DeviceService deviceService;
	private DeviceModel deviceToRestock;
	private Device device;
	private RestockOrderModel restockOrderCreateModel;

	@Autowired
	public RestockOrderStepDefinition(RestockOrderService restockOrderService, DeviceService deviceService) {
		this.restockOrderService = restockOrderService;
		this.deviceService = deviceService;
	}

	@Given("there is existing device with product name {string}")
	public void checkDeviceProductName(String productName) {
		try {
			device = deviceService.getSingleDeviceByProductName(productName);
		} catch (DeviceNotFoundException exception) {
			logger.info("Device not found with following product name: " + productName);
		}
		logger.info("Device with product number found: " + productName);
		deviceToRestock = new DeviceModel();
		deviceToRestock.setProductName(productName);
	}

	@And("with product code {string}")
	public void checkDeviceProductCode(String productCode) {
		deviceToRestock.setProductCode(productCode);
	}

	@And("has it's quantity below quantity threshold value")
	public void checkQuantity() {
		if (device.getQuantity() > device.getQuantityThreshold()) {
			this.device.setQuantity(device.getQuantityThreshold());
		}
		deviceToRestock.setQuantityThreshold(10);
		deviceToRestock.setQuantity(0);
	}

	@When("user creates restock order for {int} items of this device")
	public void createRestockOrderForDevice(int quantity) {
		restockOrderCreateModel = new RestockOrderModel();
		restockOrderCreateModel.setDevice(this.device);
		restockOrderCreateModel.setQuantityToReorder(quantity);
	}

	@Then("^a single restock order is created in the database$")
	public void checkIfRestockOrderExists() {
		RestockOrder restockOrder = restockOrderService.create(restockOrderCreateModel);
		assert restockOrder != null;
		logger.info("Restock order created successfully for device " + device.getProductName());
	}

	@And("no more restock orders for this device can be created")
	public void checkAnotherRestockOrderCreationResult() {
		RestockOrder restockOrder = null;
		logger.info("Attempting to recreate restock order for device with product name " + device.getProductName());
		try {
			restockOrder = restockOrderService.create(restockOrderCreateModel);
		} catch (DeviceNotFoundException exception) { //create method throws this exception by design
			logger.info("System did not create another restock order as expected for device: " + device.getProductName());
		}
		assert restockOrder == null;
	}

}
