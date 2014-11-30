# README #

* This plugin creates an editor for OpenCL kernel source (based on the CDT C editor),
  registers a content type for OpenCL kernel source files and validate kernel code using pre-defined validation rules.

## Roadmap ##
* Highlighting of OpenCL keywords in the OpenCL editor
* CODAN checker for memory space usage: based on the variable declarations checks that the same memory space is used in pointer casts. Also checks variable declarations for incorrect use of memory space.