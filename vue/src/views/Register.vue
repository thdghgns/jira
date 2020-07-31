<template>
  <v-container
    id="register"
    fluid
    tag="section"
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="8"
      >
        <base-material-card>
          <template v-slot:heading>
            <div class="display-2 font-weight-light">
              Join us
            </div>
          </template>

          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col
                  cols="12"
                  md="12"
                >
                  <v-text-field
                    label="Username"
                    class="purple-input"
                    v-model="user.username"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="12"
                >
                  <v-text-field
                    type="email"
                    label="Email"
                    class="purple--text"
                    v-model="user.email"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="12"
                >
                  <v-text-field
                    type="password"
                    label="Password"
                    class="purple--text"
                    v-model="user.password"
                  />
                </v-col>

                <v-col
                  cols="12"
                  class="text-right"
                >
                  <v-btn
                    color="success"
                    class="mr-0"
                    @click="handleRegister"
                  >
                    SignUp
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </base-material-card>
      </v-col>

    </v-row>
  </v-container>
</template>

<script>
  import User from '../models/user'

  export default {
    name: 'Login',
    data () {
      return {
        user: new User('', '', ''),
        message: '',
      }
    },
    computed: {
      loggedIn () {
        return this.$store.state.auth.status.loggedIn
      },
    },
    created () {
      if (this.loggedIn) {
        this.$router.push('/')
      }
    },
    methods: {
      handleRegister () {
        if (this.user.username && this.user.email && this.user.password) {
          this.$store.dispatch('auth/register', this.user).then(
            () => {
              alert('가입을 축하드립니다.')
              this.$router.push('/login')
            },
            error => {
              console.log(error.message)
              alert('가입에 실패하였습니다.')
            },
          )
        } else {
          alert('ID/EMAIL/PW를 입력하세요.')
        }
      },
    },
  }
</script>
