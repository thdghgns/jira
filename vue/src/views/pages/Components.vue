<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container id="components" fluid tag="section">
    <base-material-card icon="mdi-clipboard-text" title="Components" class="px-5 py-3">
      <v-row>
        <v-col cols="12" md="8">
          <v-text-field v-model="search" label="search" />
        </v-col>
        <v-col cols="12" md="4">
          <v-btn class="mx-2" fab color="green"
                 @click="handleCreateBtnClick">
            <v-icon>mdi-plus</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-data-table id="grid" :headers="headers" :items="comps" :items-per-page="pageSize"
                    :search="search" :single-select="true" item-key="key" class="elevation-1"
                    :footer-props="footerProps">
        <template v-slot:item="row">
          <tr>
            <td>{{ row.item.key }}</td>
            <td>{{ row.item.name }}</td>
            <td>{{ row.item.description }}</td>
            <td align="center">
              <v-btn fab dark x-small
                     color="red" @click="handleTableDeleteRowClick(row.item)">
                <v-icon>mdi-minus</v-icon>
              </v-btn>
              <v-btn fab x-small dark
                     color="blue" @click="handleTableEditRowClick(row.item)">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>
    </base-material-card>

    <v-dialog v-model="dialogDelete" max-width="500px">
      <v-card>
        <v-card-title>Delete Component</v-card-title>
        <v-card-text>Do you really want to delete the item `{{itemToDelete.key}}`?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialogDelete = false">Close</v-btn>
          <v-btn color="primary" text @click="deleteItem()">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="dialogUpdate" max-width="600px">
      <v-card>
        <v-card-title>Update Component</v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field label="Key" readonly v-model="itemToUpdate.key"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Name" required v-model="itemToUpdate.name"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Description" v-model="itemToUpdate.description"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialogUpdate = false">Close</v-btn>
          <v-btn color="primary" text @click="updateItem()">Update</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="dialogCreate" max-width="600px">
      <v-card>
        <v-card-title>Create Component</v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field label="Key" required v-model="itemToCreate.key"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Name" required v-model="itemToCreate.name"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Description" v-model="itemToCreate.description"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
          Once created, the key cannot be modified.
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialogCreate = false">Close</v-btn>
          <v-btn color="primary" text @click="createItem()">Create</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
  // Components
  import authHeader from '../../services/auth-header'

  export default {
    name: 'Components',

    components: {
    },

    data: () => ({
      pageSize: 10,
      page: 1,
      footerProps: { 'items-per-page-options': [5, 10, 15, 30, 50] },
      search: '',
      selected: [],
      headers: [
        {
          sortable: false,
          text: 'key',
          value: 'key',
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
          text: 'description',
          value: 'description',
          align: 'left',
          class: 'primary--text display-1',
        },
        {
          sortable: false,
          text: 'actions',
          value: 'actions',
          align: 'center',
          class: 'primary--text display-1',
        },
      ],
      comps: [],
      dialogDelete: false,
      itemToDelete: [],
      dialogUpdate: false,
      itemToUpdate: [],
      dialogCreate: false,
      itemToCreate: [],
    }),

    methods: {
      getItemPerPage () {
        const baseURI = process.env.VUE_APP_API_SERVER

        this.$http.get(`${baseURI}/api/component`, {
          headers: authHeader(),
        }).then((result) => {
          console.log(result)
          this.comps = result.data
        })
      },
      handleTableDeleteRowClick (item) {
        this.itemToDelete = item
        this.dialogDelete = !this.dialogDelete
      },
      deleteItem () {
        const baseURI = process.env.VUE_APP_API_SERVER

        this.$http.delete(`${baseURI}/api/component/` + this.itemToDelete.key, {
          headers: authHeader(),
        }).then((result) => {
          console.log(result)
        })

        this.dialogDelete = false
        this.getItemPerPage()
      },
      handleTableEditRowClick (item) {
        this.itemToUpdate = item
        this.dialogUpdate = !this.dialogUpdate
      },
      updateItem () {
        const baseURI = process.env.VUE_APP_API_SERVER

        this.$http.put(`${baseURI}/api/component/`, this.itemToUpdate, {
          headers: authHeader(),
        }).then((result) => {
          console.log(result)
        })

        this.dialogUpdate = false
        this.getItemPerPage()
      },
      handleCreateBtnClick () {
        this.dialogCreate = !this.dialogCreate
      },
      createItem () {
        const baseURI = process.env.VUE_APP_API_SERVER
        var postData = {
          key: this.itemToCreate.key,
          name: this.itemToCreate.name,
          description: this.itemToCreate.description,
        }

        this.$http.post(`${baseURI}/api/component/`, postData, {
          headers: authHeader(),
        }).then((result) => {
          console.log(result)
        })

        this.dialogCreate = false
        this.itemToCreate = []
        this.getItemPerPage()
      },
    },

    created () {
      this.$i18n.locale = 'en'
      this.getItemPerPage()
    },

    beforeDestroy () {
      this.$i18n.locale = 'en'
    },
  }
</script>
