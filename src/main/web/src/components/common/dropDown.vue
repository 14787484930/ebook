<template>
    <button  class="button  button-light icon-right  ion-android-arrow-dropdown" @click="showDrop">{{title}}</button>
</template>

<script>
    export default {
        name: "DropDown",
        props: ['title', 'list','onSelect','dateDrop'],
        mounted() {
        },
        methods: {
            showDrop() {
                if(this.dateDrop){
                    this.datePicker = this.$createDatePicker({
                        title: this.title,
                        min: new Date(2008, 7, 8),
                        max: new Date(2020, 9, 20),
                        value: new Date(),
                        onSelect: this.selectHandle,
                        onCancel: this.cancelHandle
                    })
                    this.datePicker.show()
                }
                else {
                    this.picker = this.$createPicker({
                        title: this.title,
                        data: [this.list],
                        onSelect: this.selectHandle,
                        onCancel: this.cancelHandle
                    })
                    this.picker.show()
                }

            },
            selectHandle(val, index,text) {
                if(typeof (this.onSelect)=="function"){
                    this.onSelect(val,index,text);
                }
                else {
                    console.error("选择完成后的方法（onSelect）没有定义");
                }
            },
            cancelHandle() {

            }
        }
    }
</script>

<style scoped>

</style>