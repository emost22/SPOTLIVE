import { extend } from 'vee-validate';
import {
  required, 
  max,
  size,
} from 'vee-validate/dist/rules';

extend('required', {
  ...required,
  message: '필수 입력항목입니다 :)'
})
extend('max', {
  ...max,
  params: ['length'],
  message: '최대 {length}자 입니다 :)'
})
extend('size',{
  ...size,
  params: ['size'],
  message: '최대 1MB 첨부 가능합니다 :)'
}) 