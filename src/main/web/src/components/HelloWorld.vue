<script src="../../babel.config.js"></script>
<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <cube-tab-bar
   v-model="selectedLabelDefault"
   :data="tabs"
   @click="clickHandler"
   @change="changeHandler">
    </cube-tab-bar>
  </div>
</template>

<script>
  export default {
  name: 'HelloWorld',
  props: {
  msg: String
  },
  data () {
      return {
          selectedLabelDefault: 'Vip',
          tabs: [{
              label: 'Home',
              icon: 'cubeic-home'
          }, {
              label: 'Like',
              icon: 'cubeic-like'
          }, {
              label: 'Vip',
              icon: 'cubeic-vip'
          }, {
              label: 'Me',
              icon: 'cubeic-person'
          }]
      }
  },
  methods:{
      clickHandler(label){
          console.log(label)
      },
      changeHandler(label){
          // if you clicked different tab, this methods can be emitted
      }
  }
  }
</script>

