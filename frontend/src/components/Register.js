import React, {Component} from 'react';
import { Form, Input, Button, message } from 'antd';
import { API_ROOT } from '../const/constant.js';
import { Link } from "react-router-dom";
import axios from 'axios'

class RegistrationForm extends Component {
   state = {
       confirmDirty: false,
       autoCompleteResult: [],
   };

   handleConfirmBlur = e => {
       const { value } = e.target;
       this.setState({ confirmDirty: this.state.confirmDirty || !!value });
   };

   compareToFirstPassword = (rule, value, callback) => {
       const { form } = this.props;
       if (value && value !== form.getFieldValue('password')) {
           callback('Two passwords that you enter is inconsistent!');
       } else {
           callback();
       }
   };

   validateToNextPassword = (rule, value, callback) => {
       const { form } = this.props;
       if (value && this.state.confirmDirty) {
           form.validateFields(['confirm'], { force: true });
       }
       callback();
   };

   handleSubmit = e => {
       e.preventDefault();
       this.props.form.validateFields((err, values) => {
           if (!err) {
               console.log('Received values of form: ', values);
               axios.post("http://localhost:8080/register", values)
                   .then(response => {
                       console.log(response);
                       this.props.history.push('/login');
                   })
                   .catch(error => {
                       console.log('error in register', error);
                   });
           }
       });
   };

   render() {
       const { getFieldDecorator } = this.props.form;

       const formItemLayout = {
           labelCol: {
               xs: { span: 24 },
               sm: { span: 8 },
           },
           wrapperCol: {
               xs: { span: 24 },
               sm: { span: 16 },
           },
       };
       const tailFormItemLayout = {
           wrapperCol: {
               xs: {
                   span: 24,
                   offset: 0,
               },
               sm: {
                   span: 16,
                   offset: 8,
               },
           },
       };

       return (
           <Form {...formItemLayout} onSubmit={this.handleSubmit} className="register">
               <Form.Item label="Username" >
                   {
                     getFieldDecorator('email', {
                       rules: [{ required: true, message: 'Please input your username!' }],
                     })(<Input />)
                   }
               </Form.Item>
               <Form.Item label="Password" hasFeedback>
                   {getFieldDecorator('password', {
                       rules: [
                           {
                               required: true,
                               message: 'Please input your password!',
                           },
                           {
                               validator: this.validateToNextPassword,
                           },
                       ],
                   })(<Input.Password />)}
               </Form.Item>
               <Form.Item label="Confirm Password" hasFeedback>
                   {getFieldDecorator('confirm', {
                       rules: [
                           {
                               required: true,
                               message: 'Please confirm your password!',
                           },
                           {
                               validator: this.compareToFirstPassword,
                           },
                       ],
                   })(<Input.Password onBlur={this.handleConfirmBlur} />)}
               </Form.Item>
               <Form.Item {...tailFormItemLayout}>
                   <Button type="primary" htmlType="submit">
                       Register
                   </Button>
                   {<p>I already have an account, go back to <Link to="/login">login</Link></p>}
               </Form.Item>
           </Form>
       );
   }
}

export const Register = Form.create({ name: 'register' })(RegistrationForm);
