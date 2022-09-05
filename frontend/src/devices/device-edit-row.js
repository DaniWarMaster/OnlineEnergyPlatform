import React from 'react';
import validate from "./device-validator";
import Button from "react-bootstrap/Button";
import * as API_DEVICES from "./device-api";
import APIResponseErrorMessage from "../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';



class EditDeviceForm extends React.Component {

    constructor(props) {
        super(props);
        this.reloadHandler = this.props.reloadHandler;
        this.cancelAction = this.props.cancelAction;
        this.deviceEdit = this.props.deviceEdit;

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls: {
                description: {
                    value: this.deviceEdit == null ? '' : this.deviceEdit.description,
                    placeholder: 'Functionality of device...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                address: {
                    value: this.deviceEdit == null ? '' : this.deviceEdit.address,
                    placeholder: 'Address of device...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                maximumEnergyConsumption: {
                    value: this.deviceEdit == null ? '' : this.deviceEdit.maximumEnergyConsumption,
                    placeholder: 'Maximum Consumption of device...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
                averageEnergyConsumption: {
                    value: this.deviceEdit == null ? '' : this.deviceEdit.averageEnergyConsumption,
                    placeholder: 'Average Consumption of device...',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    updateDevice(device) {
        return API_DEVICES.updateDevice(device, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted device with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleSubmit() {
        var device = this.deviceEdit;
        device.description = this.state.formControls.description.value;
        device.address = this.state.formControls.address.value;
        device.maximumEnergyConsumption = this.state.formControls.maximumEnergyConsumption.value;
        device.averageEnergyConsumption = this.state.formControls.averageEnergyConsumption.value;

        console.log(device);
        this.updateDevice(device);
    }

    render() {
        return (
            <div>

                <FormGroup id='description'>
                    <Label for='descriptionField'> Description: </Label>
                    <Input name='description' id='descriptionField' placeholder={this.state.formControls.description.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.description.value}
                           touched={this.state.formControls.description.touched? 1 : 0}
                           valid={this.state.formControls.description.valid}
                           required
                    />
                    {this.state.formControls.description.touched && !this.state.formControls.description.valid &&
                    <div className={"error-message row"}> * Description must have at least 3 characters </div>}
                </FormGroup>

                <FormGroup id='address'>
                    <Label for='addressField'> Address: </Label>
                    <Input name='address' id='addressField' placeholder={this.state.formControls.address.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.address.value}
                           touched={this.state.formControls.address.touched? 1 : 0}
                           valid={this.state.formControls.address.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='maximumEnergyConsumption'>
                    <Label for='maximumEnergyConsumptionField'> Maximum Energy Consumption: </Label>
                    <Input name='maximumEnergyConsumption' id='maximumEnergyConsumptionField' placeholder={this.state.formControls.maximumEnergyConsumption.placeholder}
                           min={0} max={100} type="number"
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.maximumEnergyConsumption.value}
                           touched={this.state.formControls.maximumEnergyConsumption.touched? 1 : 0}
                           valid={this.state.formControls.maximumEnergyConsumption.valid}
                           required
                    />
                </FormGroup>

                <FormGroup id='averageEnergyConsumption'>
                    <Label for='averageEnergyConsumptionField'> Average Energy Consumption  : </Label>
                    <Input name='averageEnergyConsumption' id='averageEnergyConsumptionField' placeholder={this.state.formControls.averageEnergyConsumption.placeholder}
                           min={0} max={100} type="number"
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.averageEnergyConsumption.value}
                           touched={this.state.formControls.averageEnergyConsumption.touched? 1 : 0}
                           valid={this.state.formControls.averageEnergyConsumption.valid}
                           required
                    />
                </FormGroup>

                    <Row>
                        <Col sm={{size: '4', offset: 8}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>  Submit </Button>
                            <Button type={"button"} onClick={this.cancelAction}>  Cancel </Button>
                        </Col>
                    </Row>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default EditDeviceForm;
