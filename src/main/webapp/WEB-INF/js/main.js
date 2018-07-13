import React, {Component} from 'react';
import ReactDOM from "react-dom";
import BookForm from './components/BookForm'


$(function () {
    ReactDOM.render(<BookForm />, document.getElementById("bookModal"));
});
