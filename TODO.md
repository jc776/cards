## Code
- transit clj seems over-the-top - "byte streams" doesn't help here

## Editors

**ProtoREPL**
- ProtoREPL can't talk to CLJ + CLJS together (single connection)
- ProtoREPL can't autocomplete/etc for CLJS (doesn't use CIDER)
- Not sure how eval-on-save interacts w/ shadow-cljs
- Indirect stdout doesn't go to REPL (I think that's a CIDER thing)

**VSCode**
- Parinfer is broken (building latest fixes was *not terrible*)
- Calva doesn't have 'auto eval'
- Calva's 'eval-on-save' doesn't show inline results
