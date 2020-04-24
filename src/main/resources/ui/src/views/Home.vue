<template>
  <div>
    <div v-if="showLogin">
      <h3>Enter your username</h3>
      <input v-model="username" />
      <button @click="login()">Login</button>
    </div>
    <div v-else>
      <input v-model="currentMsg" />
      <button @click="sendMessage()">Send</button>
      <!-- <input type="file" /> -->
      <div v-for="(x, i) in messages" :key="i">
        {{ x.username + " " }}
        {{ x.message }}
      </div>
    </div>
  </div>
</template>

<script lang="js">
import Stomp from "stompjs";
import SockJS from "sockjs-client";

export default {
  name: "home",
  data() {
    return {
      currentMsg: '',
      showLogin: true,
      stompClient: null,
      messages: [],
      attachment: null,
      username: '',
    };
  },
  components: {},
  mounted() {
    this.initializeClient();
  },
  methods: {
    initializeClient() {
      try {
        const socket = new SockJS("/chat-websocket");

        this.stompClient = Stomp.over(socket)
        this.stompClient.debug = null;

        this.stompClient.connect({}, (frame) => {
          this.stompClient.subscribe("/topic/emit-msg", (response) => {
            const body = JSON.parse(response.body);
            const msg = { username: body.username, message: body.message };

            if (body.attachment) {
              msg.attachment = body.attachment;
            }

            this.messages.push(msg)
          });
      });
      } catch(e) {
        console.error(e);
      }
    },
    login() {
      if (this.username.length) {
        this.showLogin = false;
      }
    },
    sendMessage() {
      if (!this.stompClient || this.currentMsg.length === 0) return;

        const msg = { username: this.username, message: this.currentMsg };

        if (this.attachment) {
          msg.attachment = this.attachment;
        }

        this.stompClient.send("/app/send-msg", {}, JSON.stringify(msg));
        this.currentMsg = '';
        this.attachment = null;
    }
  }
};
</script>

<style scoped lang="scss"></style>
