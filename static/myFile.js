import Quill from 'quill'
const Inline = Quill.import('blots/inline')
class file extends Inline {
  static create () {
    const node = super.create()
    return node
  }

  static formats (node) {
    return node
  }
}
file.blotName = 'file'
file.tagName = 'span'
export default file
