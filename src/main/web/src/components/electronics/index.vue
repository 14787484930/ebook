<template>
 <div>
  <van-button type="warning">警告按钮</van-button>
  <van-popup v-model="isShow" position="bottom " :overlay="true">{{msg}}</van-popup>
 </div>
</template>

<script>
export default {
  name: 'Teach',
  data () {
    return {
        isShow:true,
      msg: '电子'
    }
  }
}
</script>

