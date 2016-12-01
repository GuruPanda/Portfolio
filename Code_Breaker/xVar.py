import ast
import random
from collections import Counter
import sys

class Variables(ast.NodeVisitor):
    varlst=[]
    def visit_Name(self, node):
        self.varlst.append(node.id)
        self.generic_visit(node)

class VarSwap(ast.NodeTransformer):
    fileName = sys.argv[1]
    inp = (open(fileName, 'r'))
    code = inp.read()
    tree = ast.parse(code)
    leaf = Variables()
    leaf.visit(tree)
    a = leaf.varlst[random.randint(0,len(leaf.varlst)-1)]
    b = leaf.varlst[random.randint(0,len(leaf.varlst)-1)]
    count = Counter(leaf.varlst)
    ranA = random.randint(0,(count[a])-1)
    ranB = random.randint(0,(count[b])-1)
    def visit_Name(self, node):
        if node.id==self.a:
            self.ranA=self.ranA-1
            if self.ranA==0:
                return ast.copy_location(ast.Name(id=self.b,
                        ctx=node.ctx
                    ), node)
            return node
        elif node.id==self.b:
            self.ranB=self.ranB-1
            if self.ranB==0:
                return ast.copy_location(ast.Name(id=self.b,
                        ctx=node.ctx
                    ), node)
            return node
        else:
            return node

fileName = sys.argv[1]
inp = (open(fileName, 'r'))
code = inp.read()
tree = ast.parse(code)
branch = VarSwap()
branch.visit(tree)
ast.fix_missing_locations(tree)
co = compile(tree, "<ast>", "exec")
exec(co)
