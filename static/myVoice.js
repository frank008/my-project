import Quill from 'quill'
const Inline = Quill.import('blots/inline')
class audio extends Inline {
  static create () {
    const node = super.create()
    return node
  }

  static formats (node) {
    return node
  }
}
audio.blotName = 'audio'
audio.tagName = 'span'
export default audio
