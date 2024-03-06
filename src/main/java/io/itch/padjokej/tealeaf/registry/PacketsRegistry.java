package io.itch.padjokej.tealeaf.registry;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class PacketsRegistry
{
    public static final Identifier BOIL_PARTICLE_PACKET = new Identifier(TeaLeaf.MOD_ID, "boil_particle_packet");
}
