# README #

* This plugin creates an editor for OpenCL kernel source (based on the CDT C editor). Highlights OpenCL keywords and validates kernel code using pre-defined validation rules.

## Roadmap ##
* Create symbols to map OpenCL language elements to C language (define, typedef rules).
* CODAN checker for memory space usage: based on the variable declarations checks that the same memory space is used in pointer casts. Also checks variable declarations for incorrect use of memory space.