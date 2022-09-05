import React from "react";
import Table from "../commons/tables/table";
import Button from "react-bootstrap/Button";

const columns = [
    {
        Header: 'Description',
        accessor: 'description',
    },
    {
        Header: 'Address',
        accessor: 'address',
    },
    {
        Header: 'Maximum Energy Consumption',
        accessor: 'maximumEnergyConsumption',
    },
    {
        Header: 'Average Energy Consumption',
        accessor: 'averageEnergyConsumption',
    }
];

const filters = [
    {
        accessor: 'description',
    }
];

class DeviceTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tableData: this.props.tableData,
            handleEventUpdate: this.props.handleEventUpdate,
            handleDeleteEvent: this.props.handleDeleteEvent
        };
    }

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={[
                     {
                         Header: 'Description',
                         accessor: 'description',
                     },
                     {
                         Header: 'Address',
                         accessor: 'address',
                     },
                     {
                         Header: 'Maximum Energy Consumption',
                         accessor: 'maximumEnergyConsumption',
                     },
                     {
                         Header: 'Average Energy Consumption',
                         accessor: 'averageEnergyConsumption',
                     },
                      {
                          Header: 'Edit',
                          accessor: 'edit',
                          Cell: ({original}) => (
                                <Button id = {original.id} onClick = {this.state.handleEventUpdate}> Edit </Button>
                          )
                      },
                        {
                             Header: 'Delete',
                             accessor: 'delete',
                             Cell: ({original}) => (
                                   <Button id = {original.id} onClick = {this.state.handleDeleteEvent}> Delete </Button>
                             )
                         }
                 ]}
                search={filters}
                pageSize={5}
            />
        )
    }
}

export default DeviceTable;