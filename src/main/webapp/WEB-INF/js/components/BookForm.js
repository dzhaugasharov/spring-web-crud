import React, {Component} from 'react';
import Modal from "./Modal";


class FormGroup extends React.Component {
    render() {
        const {label, fieldName, errors} = this.props;
        return (
            <div className="form-group">
                <label>{label}</label>
                <input name={fieldName} value="" className="form-control"/>
                <div className="error-block">
                </div>
            </div>
        )
    }
}

export default class BookForm extends Component {
    constructor() {
        super();
        this.state = {
            title: ""
        };
    }

    render() {
        const {title} = this.props;

        return (
            <Modal title={"Books "}>
                <form method="post">
                    <input type="hidden" name="id" value=""/>
                    <FormGroup label="Название" fieldName={"title"}/>
                    <FormGroup label="Автор" fieldName={"author"}/>
                </form>
            </Modal>
        );
    }
}
