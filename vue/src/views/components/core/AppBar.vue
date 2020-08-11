<template>
  <v-app-bar
    id="app-bar"
    absolute
    app
    color="transparent"
    flat
    height="75"
  >
    <v-btn
      class="mr-3"
      elevation="1"
      fab
      small
      @click="setDrawer(!drawer)"
    >
      <v-icon v-if="value">
        mdi-view-quilt
      </v-icon>

      <v-icon v-else>
        mdi-dots-vertical
      </v-icon>
    </v-btn>

    <v-toolbar-title
      class="hidden-sm-and-down font-weight-light"
      v-text="$route.name"
    />

    <v-spacer />
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
          v-if="username"
        >
          {{ username }}
        </v-btn>
      </template>
      <v-list>
        <v-list-item
          key="logout"
          value="logout"
          @click="logout"
        >
          logout
        </v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<script>
  // Components

  // Utilities
  import { mapState, mapMutations } from 'vuex'

  export default {
    name: 'DashboardCoreAppBar',

    components: {
    },

    props: {
      value: {
        type: Boolean,
        default: false,
      },
    },

    data: () => ({
    }),

    computed: {
      ...mapState('dashboard', ['drawer']),
      username: function () {
        return JSON.parse(localStorage.getItem('user')).username
      },
    },

    methods: {
      ...mapMutations('dashboard', {
        setDrawer: 'SET_DRAWER',
      }),
      logout: function () {
        this.$store.dispatch('auth/logout')
        this.$router.push('/')
      },
    },
  }
</script>
