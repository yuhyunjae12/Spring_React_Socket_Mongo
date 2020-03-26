import React, { Component } from 'react'
import SockJsClient from 'react-stomp'
import { TalkBox } from "react-talk";
import Axios from 'axios';

class App extends Component {
  constructor(props){
    super(props)
    this.state ={
        messages: [],
        sendMsg: ''
    }
  }

  componentDidMount (){

    Axios.get('http://localhost:8080/messages/1')
    .then((res) =>{
      console.log(res.data)
      this.setState({
        messages: res.data
      })
    })

  }

  setStateValue = (e) =>{
    this.setState({[e.target.name]: e.target.value})
  }

  onMessageReceive = (msg) => {
    this.setState(prevState => ({
      messages: [...prevState.messages, msg]
    }));
  }

  sendMessage = () => {

    try {

      const msgData ={
        userName: 'hyun'
        , message: this.state.sendMsg
        , roomId: 1
      }
  
      this.clientRef.sendMessage("/app/send", JSON.stringify(msgData));
      this.setState({sendMsg: ''})

      return true;
    } catch(e) {
      return false;
    }
  }

  render() {
    return (
      <div>
        
        {
          this.state.messages.map((val) =>{
          return <div key={val.id}>{val.userName} : {val.message} [{val.messageTime}]</div>
          })
        }

        <input type="text" name="sendMsg" onChange={this.setStateValue} value={this.state.sendMsg} />
        <button
          onClick={this.sendMessage}
        >Send</button>

        <SockJsClient url='http://localhost:8080/ws-hyun' topics={['/topic/chat/' + '1']}
            onMessage={ this.onMessageReceive }
            ref={ (client) => { this.clientRef = client }} />
      </div>
    )
  }
}

export default App