<template>
  <v-container
    id="rtl"
    fluid
    tag="section"
  >
    <v-row class="mb-10">
      <v-col
        cols="12"
        md="12"
      >
        <base-material-card
          text="Components"
        >
          <v-card-text>
            <v-data-table
              :headers="headers"
              :items="items"
              :loading="loading"
            />
          </v-card-text>
        </base-material-card>
      </v-col>

      <v-col
        cols="12"
        md="6"
      >
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  // Components

  export default {
    name: 'Components',

    components: {
    },

    data: () => ({
      headers: [
        {
          sortable: false,
          text: 'id',
          value: 'id',
          align: 'left',
          class: 'primary--text display-1',
        },
        {
          sortable: false,
          text: 'name',
          value: 'name',
          align: 'left',
          class: 'primary--text display-1',
        },
        {
          sortable: false,
          text: 'salary',
          value: 'salary',
          align: 'left',
          class: 'primary--text display-1',
        },
        {
          sortable: false,
          text: 'country',
          value: 'country',
          align: 'left',
          class: 'primary--text display-1',
        },
        {
          sortable: false,
          text: 'city',
          value: 'city',
          align: 'left',
          class: 'primary--text display-1',
        },
      ],
      items: [
        {
          id: 1,
          name: 'user1',
          country: 'ROK',
          city: 'SEOUL',
          salary: '$35,738',
        },
        {
          id: 2,
          name: 'user2',
          country: 'ROK',
          city: 'SEOUL',
          salary: '$23,738',
        },
        {
          id: 3,
          name: 'user3',
          country: 'ROK',
          city: 'SEOUL',
          salary: '$56,142',
        },
        {
          id: 4,
          name: 'user4',
          country: 'ROK',
          city: 'SEOUL',
          salary: '$38,735',
        },
        {
          id: 5,
          name: 'user5',
          country: 'ROK',
          city: 'SEOUL',
          salary: '$63,542',
        },
        {
          id: 6,
          name: 'user6',
          country: 'ROK',
          city: 'SEOUL',
          salary: '$43,542',
        },
      ],
      loading: false,
    }),

    created () {
      this.$i18n.locale = 'en'
    },

    beforeDestroy () {
      this.$i18n.locale = 'en'
    },
  }
</script>
