<template>
  <div id="app">
    <div class="tabs tabs-icon-top">
      <div v-for="(item) in config"  class="tab-item"    @click="changeSelected(item.component)">
        <i v-if="seleted==item.component" :class="['icon',item.iClass]" style="color:#379be9"></i>
        <i v-else :class="['icon',item.iOutClass]"  style="color:#379be9"></i>
        {{item.text}}
      </div>
    </div>
    <component :is="seleted"></component>
  </div>
</template>

<script>
import  Book from  '../components/book'
import  Electronics from  '../components/electronics'
import  Teach from  '../components/teach'
import  Other from  '../components/other'

export default {
  name: 'app',
  components: {
      Book,
      Electronics,
      Teach,
      Other
  },
    data () {
      return {
          seleted:'Book',
          config:[
              {component: 'Book',text:"图书",iClass:"ion-ios-bookmarks",iOutClass:"ion-ios-bookmarks-outline"},
              { component: 'Electronics', text:"电子",iClass:"ion-ios-monitor",iOutClass:"ion-ios-monitor-outline"},
              {component: 'Teach', text:"辅导",iClass:"ion-ios-people",iOutClass:"ion-ios-people-outline"},
              {component: 'Other', text:"其他",iClass:"ion-ios-gear",iOutClass:"ion-ios-gear-outline"}
          ]
      }
      },
    methods:{
        changeSelected(component){
           this.seleted=component;
        }
  }
}
</script>

