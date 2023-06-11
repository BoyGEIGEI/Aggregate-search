<template>
  <a-list item-layout="horizontal" :data-source="props.postList">
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta>
          {{ item }}
          <template #title>
          
            <p v-html="item.title" style="color: black"></p>
          </template>
          <template #avatar>
            <a-avatar :src="gege" />
          </template>
          <template #description>
            <p v-html="item.content" style="color: black"></p>
            
          </template>
        </a-list-item-meta>
      </a-list-item>
    </template>
  </a-list>
</template>

<script setup lang="ts">
import gege from "../assets/gege.jpg";
import { withDefaults, defineProps } from "vue";

interface Props {
  postList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  postList: () => [],
});
</script>

<style scoped>
.gege {
  width: 200px;
}
</style>