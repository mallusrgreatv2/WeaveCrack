package com.github.mallusrgreat.weavecracked

import net.weavemc.loader.api.Hook
import net.weavemc.loader.api.util.asm
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LdcInsnNode
import org.objectweb.asm.tree.MethodNode

fun ClassNode.hasCst(vararg cst: Any): Boolean {
    return methods.flatMap { it.instructions }
        .filterIsInstance<LdcInsnNode>()
        .map { it.cst }
        .containsAll(cst.toList())
}
fun MethodNode.hasCst(vararg cst: Any): Boolean {
    return instructions
        .filterIsInstance<LdcInsnNode>()
        .map { it.cst }
        .containsAll(cst.toList())
}

class CrackedHook : Hook("net/minecraft/client/Minecraft") {
    override fun transform(classNode: ClassNode, assemblerConfig: AssemblerConfig) {
        if(!classNode.hasCst("launcher_accounts.json")) return
        val canPlayOnline = classNode.methods.reversed()
            .dropWhile { !it.hasCst("Accounts") }
            .first { it.desc == "()Z" }
        canPlayOnline.instructions = asm {
            iconst_1
            ireturn
        }
        return
    }
}
