<template>
  <v-container
    id="login"
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
              Welcome
            </div>
          </template>

          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col
                  cols="12"
                  md="6"
                >
                  <v-text-field
                    label="Username"
                    class="purple-input"
                    v-model="user.username"
                  />
                </v-col>

                <v-col
                  cols="12"
                  md="6"
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
                  md="10"
                  class="text-right"
                >
                  <v-btn
                    color="success"
                    class="mr-0"
                    @click="handleLogin"
                  >
                    Login
                  </v-btn>
                </v-col>

                <v-col
                  cols="12"
                  md="2"
                  class="text-right"
                >
                  <v-btn
                    color="orange"
                    class="mr-0"
                    @click="goToSignUpPage"
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
  import User from '../../models/user'

  export default {
    name: 'Login',
    data () {
      return {
        user: new User('', ''),
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
      handleLogin () {
        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push('/')
            },
            error => {
              console.log(error.message)
              alert('로그인에 실패하였습니다.')
            },
          )
        } else {
          alert('ID/PW를 입력하세요.')
        }
      },
      goToSignUpPage () {
        this.$router.push('/register')
      },
    },
  }
</script>
