<template>
 <div>
     <div id="head">
     <div class="bar bar-header item-input-inset">
         <label class="item-input-wrapper">
             <i class="icon ion-ios-search placeholder-icon"></i>
             <input type="search" placeholder="搜索" v-model="queryList.title">
         </label>
     </div>
         <DropDown v-for="item in dropCconfig"  :dateDrop="item.dateDrop" :title="item.title" :list="item.list"
                   :onSelect="item.onSelect"></DropDown>
     </div>
         <div class="scroll-list-wrap"  >
         <cube-scroll
                 ref="scroll"
                 :options="options"
         >
             <template>
                 <div class="list">
                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/blue-album.jpg">
                         <h2>Weezer</h2>
                         <p>Blue Album</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/siamese-dream.jpg">
                         <h2>Smashing Pumpkins</h2>
                         <p>Siamese Dream</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/nevermind.jpg">
                         <h2>Nirvana</h2>
                         <p>Nevermind</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/license-to-ill.jpg">
                         <h2>Beastie Boys</h2>
                         <p>License To Ill</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/dookie.jpg">
                         <h2>Green Day</h2>
                         <p>Dookie</p>
                     </a>
                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/blue-album.jpg">
                         <h2>Weezer</h2>
                         <p>Blue Album</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/siamese-dream.jpg">
                         <h2>Smashing Pumpkins</h2>
                         <p>Siamese Dream</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/nevermind.jpg">
                         <h2>Nirvana</h2>
                         <p>Nevermind</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/license-to-ill.jpg">
                         <h2>Beastie Boys</h2>
                         <p>License To Ill</p>
                     </a>

                     <a class="item item-thumbnail-left" href="#">
                         <img src="http://www.runoob.com/try/demo_source/dookie.jpg">
                         <h2>Green Day</h2>
                         <p>Dookie</p>
                     </a>

                 </div>

             </template>
         </cube-scroll>
     </div>
 </div>
</template>

<script>
    import  $ from 'jquery'
    import  DropDown from  '../common/dropDown'
export default {
  name: 'Book',
    components: {
        DropDown,
    },
  data () {
    return {
      msg: '图书',
        columns:[],
        queryList:{
            title:'钢铁是怎样炼成的',
            type:'',
            date:'',
            level:'',
        },
        dropCconfig:[],
    }
  },

    computed: {
        options() {
            return {
                scrollbar:{ fade: true } ,//或false
                startY: 0
            }
        },
    },
    created(){
      this.initDropCconfig();
    },
    mounted() {
      $(".scroll-list-wrap").height(screen.availHeight-$("#head").height()-$(".tabs-icon-top",window.parent.parent.document).height());
    },
    methods: {
        initDropCconfig(){
            this.dropCconfig=[
                {title: '类型选择',
                    list: [{ text: '青春文学', value: '青春文学'}, { text: '热门小说', value: '热门小说' }],
                    onSelect:(val,index,text)=>{
                       console.log(val);
                        console.log(index);
                        console.log(text);
                    }
                },
                {title:'日期',dateDrop:true,onSelect:(date,val,text)=>{
                        console.log(date);
                        console.log(val);
                        console.log(text);
                    }},
                {title:'价格',list:[{ text: '剧毒1', value: '剧毒1'}, { text: '蚂蚁1', value: '蚂蚁1' }],
                    onSelect:(val,index,text)=>{
                        console.log(val);
                        console.log(index);
                        console.log(text);
                    }}
            ];
        },
    }
}
</script>
<style lang="stylus" rel="stylesheet/stylus">
    .scroll-list-wrap {
        border: 1px solid rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        transform: rotate(0deg); // fix 子元素超出边框圆角部分不隐藏的问题
        overflow: hidden;
    }
</style>
